# CG Butterfly Tracker

An Android app (Kotlin + Jetpack Compose) for logging butterfly sightings in Chhattisgarh, capturing the GPS location of each sighting, and visualizing the route you walked.

## What it does

- **Species picker** pre-seeded with ~55 butterflies documented in published Chhattisgarh surveys (Achanakmar-Amarkantak Biosphere Reserve, Udanti-Sitanadi Tiger Reserve, and district checklists for Dhamtari/Gariaband/Raipur/Bilaspur/Bastar/Raigarh) — common name, scientific name, family, and rarity notes. Search/filter as you type.
- **One-tap sighting log**: pick the species, optionally add a note, tap "Capture location & save" — it grabs your current GPS fix and stores the sighting locally (Room database, works fully offline).
- **Route tracking**: "Start route" begins recording a breadcrumb trail (via `FusedLocationProviderClient`) as you walk; sightings made during that trip are pinned along the route. "Route" screen draws the path and markers on a simple canvas map.
- **Home screen** lists all sightings chronologically with species counts.

## Project structure

```
app/src/main/java/com/cgnature/butterflytracker/
├── MainActivity.kt
├── data/
│   ├── ChhattisgarhButterflies.kt   ← the seed species list (edit this to add more)
│   ├── Sighting.kt                  ← Room entities: Sighting, Trip, RoutePoint
│   ├── SightingDao.kt
│   ├── AppDatabase.kt
│   └── SightingRepository.kt
├── location/
│   └── LocationTracker.kt           ← FusedLocationProviderClient wrapper
├── viewmodel/
│   └── SightingViewModel.kt
├── navigation/
│   └── AppNavigation.kt
└── ui/
    ├── theme/Theme.kt
    └── screens/
        ├── DashBoardScreen.kt
        ├── AddSightingScreen.kt
        └── RouteScreen.kt
```


## Notes on data & privacy

All data is stored **locally on the device** in a Room (SQLite) database — nothing is uploaded anywhere.
