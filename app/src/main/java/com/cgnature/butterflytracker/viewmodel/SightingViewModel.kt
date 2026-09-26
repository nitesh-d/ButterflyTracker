package com.cgnature.butterflytracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cgnature.butterflytracker.data.AppDatabase
import com.cgnature.butterflytracker.data.ButterflySpecies
import com.cgnature.butterflytracker.data.RoutePoint
import com.cgnature.butterflytracker.data.Sighting
import com.cgnature.butterflytracker.data.SightingRepository
import com.cgnature.butterflytracker.location.LocationTracker
import com.cgnature.butterflytracker.data.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** A trip paired with a count of distinct species logged during it — powers the "Recent Checklists" list. */
data class ChecklistSummary(
    val trip: Trip,
    val speciesCount: Int,
    val sightingCount: Int,
    val butterflyCount: Int,
    val plantCount: Int,
    val birdCount: Int
)

class SightingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SightingRepository(
        AppDatabase.getInstance(application).sightingDao()
    )
    private val locationTracker = LocationTracker(application)

    /** Full butterfly picklist, parsed from assets/butterflies.csv. Empty until loaded. */
    private val _speciesList = MutableStateFlow<List<ButterflySpecies>>(emptyList())
    val speciesList: StateFlow<List<ButterflySpecies>> = _speciesList

    /** Plant reference list, parsed from assets/plants.csv. Empty until loaded. */
    private val _plantList = MutableStateFlow<List<com.cgnature.butterflytracker.data.PlantSpecies>>(emptyList())
    val plantList: StateFlow<List<com.cgnature.butterflytracker.data.PlantSpecies>> = _plantList

    /** Bird reference list, parsed from assets/birds.csv. Empty until loaded. */
    private val _birdList = MutableStateFlow<List<com.cgnature.butterflytracker.data.BirdSpecies>>(emptyList())
    val birdList: StateFlow<List<com.cgnature.butterflytracker.data.BirdSpecies>> = _birdList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _speciesList.value = com.cgnature.butterflytracker.data.ChhattisgarhButterflies.load(application)
        }
        viewModelScope.launch(Dispatchers.IO) {
            _plantList.value = com.cgnature.butterflytracker.data.ChhattisgarhFlora.load(application)
        }
        viewModelScope.launch(Dispatchers.IO) {
            _birdList.value = com.cgnature.butterflytracker.data.ChhattisgarhBirds.load(application)
        }
    }

    val allSightings: StateFlow<List<Sighting>> =
        repository.allSightings.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val speciesFoundCount: StateFlow<Int> =
        repository.distinctSpeciesCount.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    /** Every trip (checklist) with its species/sighting counts, most recent first. */
    val checklists: StateFlow<List<ChecklistSummary>> =
        combine(repository.allTrips, repository.allSightings) { trips, sightings ->
            trips.map { trip ->
                val forTrip = sightings.filter { it.tripId == trip.id }
                ChecklistSummary(
                    trip = trip,
                    speciesCount = forTrip.map { it.commonName }.distinct().size,
                    sightingCount = forTrip.size,
                    butterflyCount = forTrip.count { it.category == com.cgnature.butterflytracker.data.Category.BUTTERFLY.name },
                    plantCount = forTrip.count { it.category == com.cgnature.butterflytracker.data.Category.PLANT.name },
                    birdCount = forTrip.count { it.category == com.cgnature.butterflytracker.data.Category.BIRD.name }
                )
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    /** Sightings for one specific trip — used by the trip detail screen. */
    fun sightingsForTrip(tripId: Long) = repository.sightingsForTrip(tripId)

    private val _activeTripId = MutableStateFlow<Long?>(null)
    val activeTripId: StateFlow<Long?> = _activeTripId

    private val _routePoints = MutableStateFlow<List<RoutePoint>>(emptyList())
    val routePoints: StateFlow<List<RoutePoint>> = _routePoints

    private val _lastCapturedLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val lastCapturedLocation: StateFlow<Pair<Double, Double>?> = _lastCapturedLocation

    private var routeTrackingJob: Job? = null

    /** Call when the user taps "Start route" before heading out to spot butterflies. */
    fun startTrip(name: String = "") {
        viewModelScope.launch {
            val tripId = repository.startTrip(name)
            _activeTripId.value = tripId
            beginRouteTracking(tripId)
        }
    }

    fun endTrip() {
        val tripId = _activeTripId.value ?: return
        routeTrackingJob?.cancel()
        viewModelScope.launch {
            repository.endTrip(tripId)
            _activeTripId.value = null
            _routePoints.value = emptyList()
        }
    }

    private fun beginRouteTracking(tripId: Long) {
        routeTrackingJob?.cancel()
        routeTrackingJob = viewModelScope.launch {
            locationTracker.trackRoute().collect { location ->
                repository.logRoutePoint(tripId, location.latitude, location.longitude)
            }
        }
        viewModelScope.launch {
            repository.routeForTrip(tripId).collect { points ->
                _routePoints.value = points
            }
        }
    }

    /**
     * Captures current GPS location and saves a new sighting. Works for any
     * category (butterfly, plant, bird, ...) — each tab's screen passes in
     * whichever species fields it has. If no trip is active, a single-sighting
     * trip is created on the fly so the point still has somewhere to live.
     */
    fun logSighting(
        category: com.cgnature.butterflytracker.data.Category,
        commonName: String,
        scientificName: String,
        family: String = "",
        note: String = "",
        onDone: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val location = locationTracker.getCurrentLocation()
            if (location == null) {
                onDone(false)
                return@launch
            }
            val tripId = _activeTripId.value ?: repository.startTrip().also { _activeTripId.value = it }
            repository.logSighting(
                category = category,
                commonName = commonName,
                scientificName = scientificName,
                family = family,
                latitude = location.latitude,
                longitude = location.longitude,
                accuracyMeters = location.accuracy,
                note = note,
                tripId = tripId
            )
            _lastCapturedLocation.value = location.latitude to location.longitude
            onDone(true)
        }
    }

    /** Convenience overload for the existing butterfly flow. */
    fun logSighting(species: ButterflySpecies, note: String = "", onDone: (Boolean) -> Unit) {
        logSighting(
            category = com.cgnature.butterflytracker.data.Category.BUTTERFLY,
            commonName = species.commonName,
            scientificName = species.scientificName,
            family = species.family,
            note = note,
            onDone = onDone
        )
    }

    /**
     * Logs multiple species at once, sharing a single GPS fix — for when
     * you're standing in one spot and saw several things at the same time.
     * Each entry is (commonName, scientificName, family).
     */
    fun logSightings(
        category: com.cgnature.butterflytracker.data.Category,
        entries: List<Triple<String, String, String>>,
        note: String = "",
        onDone: (Boolean) -> Unit
    ) {
        if (entries.isEmpty()) {
            onDone(false)
            return
        }
        viewModelScope.launch {
            val location = locationTracker.getCurrentLocation()
            if (location == null) {
                onDone(false)
                return@launch
            }
            val tripId = _activeTripId.value ?: repository.startTrip().also { _activeTripId.value = it }
            entries.forEach { (commonName, scientificName, family) ->
                repository.logSighting(
                    category = category,
                    commonName = commonName,
                    scientificName = scientificName,
                    family = family,
                    latitude = location.latitude,
                    longitude = location.longitude,
                    accuracyMeters = location.accuracy,
                    note = note,
                    tripId = tripId
                )
            }
            _lastCapturedLocation.value = location.latitude to location.longitude
            onDone(true)
        }
    }
}