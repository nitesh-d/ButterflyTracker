package com.cgnature.butterflytracker.data

class SightingRepository(private val dao: SightingDao) {

    val allSightings = dao.getAllSightings()
    val allTrips = dao.getAllTrips()
    val distinctSpeciesCount = dao.getDistinctSpeciesCount()

    suspend fun startTrip(name: String = ""): Long =
        dao.insertTrip(Trip(startedAtMillis = System.currentTimeMillis(), name = name))

    suspend fun endTrip(tripId: Long) {
        dao.getTrip(tripId)?.let { trip ->
            dao.updateTrip(trip.copy(endedAtMillis = System.currentTimeMillis()))
        }
    }

    suspend fun logSighting(
        category: Category,
        commonName: String,
        scientificName: String,
        family: String,
        latitude: Double,
        longitude: Double,
        accuracyMeters: Float,
        note: String,
        tripId: Long
    ): Long = dao.insertSighting(
        Sighting(
            category = category.name,
            commonName = commonName,
            scientificName = scientificName,
            family = family,
            latitude = latitude,
            longitude = longitude,
            accuracyMeters = accuracyMeters,
            timestampMillis = System.currentTimeMillis(),
            note = note,
            tripId = tripId
        )
    )

    suspend fun logRoutePoint(tripId: Long, latitude: Double, longitude: Double) {
        dao.insertRoutePoint(
            RoutePoint(
                tripId = tripId,
                latitude = latitude,
                longitude = longitude,
                timestampMillis = System.currentTimeMillis()
            )
        )
    }

    fun sightingsByCategory(category: Category) = dao.getSightingsByCategory(category.name)
    fun distinctSpeciesCountByCategory(category: Category) = dao.getDistinctSpeciesCountByCategory(category.name)

    fun routeForTrip(tripId: Long) = dao.getRouteForTrip(tripId)
    fun sightingsForTrip(tripId: Long) = dao.getSightingsForTrip(tripId)
}