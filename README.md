# CG Butterfly Tracker

An Android app (Kotlin + Jetpack Compose) for logging butterfly sightings in Chhattisgarh, capturing the GPS location of each sighting, and visualizing the route you walked.

## What it does

- **Species picker** pre-seeded with ~55 butterflies documented in published Chhattisgarh surveys (Achanakmar-Amarkantak Biosphere Reserve, Udanti-Sitanadi Tiger Reserve, and district checklists for Dhamtari/Gariaband/Raipur/Bilaspur/Bastar/Raigarh) — common name, scientific name, family, and rarity notes. Search/filter as you type.
- **One-tap sighting log**: pick the species, optionally add a note, tap "Capture location & save" — it grabs your current GPS fix and stores the sighting locally (Room database, works fully offline).
- **Route tracking**: "Start route" begins recording a breadcrumb trail (via `FusedLocationProviderClient`) as you walk; sightings made during that trip are pinned along the route. "Route" screen draws the path and markers on a simple canvas map (no Google Maps API key required).
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

## How to open and run

1. Open Android Studio (Koala/2024.1 or newer recommended).
2. **File → Open** and select the `ButterflyTracker` folder.
3. Let Gradle sync (it will download Compose, Room, Play Services Location, Navigation, and Accompanist Permissions dependencies — first sync needs internet).
4. Run on a device or emulator with **Google Play services** installed (needed for `FusedLocationProviderClient`) — most emulator images with a Play Store icon work.
5. Grant the location permission when prompted the first time you log a sighting.

Minimum SDK is 26 (Android 8.0), which covers the vast majority of devices in use.

## Adding more butterfly species

Open `data/ChhattisgarhButterflies.kt` and add entries to `seedList`:

```kotlin
ButterflySpecies("Common Name", "Scientific name", "Family", "optional notes"),
```

The state's documented butterfly fauna is 170–200+ species across published surveys, so the seed list here is a practical starting subset — extend it as you spot new ones in the field.

## Swapping the canvas route view for a real Google Map (optional)

The route screen currently draws the path on a plain `Canvas`, so the app works without any API key. If you'd like an actual map instead:

1. Add `implementation("com.google.maps.android:maps-compose:4.4.1")` and `implementation("com.google.android.gms:play-services-maps:19.0.0")` to `app/build.gradle.kts`.
2. Get a Google Maps API key and add it to `AndroidManifest.xml` under a `<meta-data android:name="com.google.android.geo.API_KEY" .../>` tag.
3. Replace the `RouteCanvas` composable in `RouteScreen.kt` with a `GoogleMap` composable, plotting `routePoints` as a `Polyline` and `sightings` as `Marker`s.

## Notes on data & privacy

All data is stored **locally on the device** in a Room (SQLite) database — nothing is uploaded anywhere. If you later want cloud backup or sharing, that would need a backend (e.g. Firebase) added on top of the `SightingRepository` layer.
