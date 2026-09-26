package com.cgnature.butterflytracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Sighting::class, Trip::class, RoutePoint::class],
    version = 2, // bumped for the Sighting.category column (butterfly/plant/bird)
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sightingDao(): SightingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "butterfly_tracker.db"
                )
                    // App is still in development with no real user base yet, so we
                    // simply wipe and recreate the DB on schema changes instead of
                    // writing a real Migration. Revisit this before any real release.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}