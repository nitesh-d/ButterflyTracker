package com.cgnature.butterflytracker.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile SightingDao _sightingDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `sightings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category` TEXT NOT NULL, `commonName` TEXT NOT NULL, `scientificName` TEXT NOT NULL, `family` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `accuracyMeters` REAL NOT NULL, `timestampMillis` INTEGER NOT NULL, `note` TEXT NOT NULL, `tripId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `trips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `startedAtMillis` INTEGER NOT NULL, `endedAtMillis` INTEGER, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `route_points` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tripId` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `timestampMillis` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '77ecef34022afefef12816883c12d637')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `sightings`");
        db.execSQL("DROP TABLE IF EXISTS `trips`");
        db.execSQL("DROP TABLE IF EXISTS `route_points`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSightings = new HashMap<String, TableInfo.Column>(11);
        _columnsSightings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("commonName", new TableInfo.Column("commonName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("scientificName", new TableInfo.Column("scientificName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("family", new TableInfo.Column("family", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("accuracyMeters", new TableInfo.Column("accuracyMeters", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("timestampMillis", new TableInfo.Column("timestampMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("note", new TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSightings.put("tripId", new TableInfo.Column("tripId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSightings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSightings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSightings = new TableInfo("sightings", _columnsSightings, _foreignKeysSightings, _indicesSightings);
        final TableInfo _existingSightings = TableInfo.read(db, "sightings");
        if (!_infoSightings.equals(_existingSightings)) {
          return new RoomOpenHelper.ValidationResult(false, "sightings(com.cgnature.butterflytracker.data.Sighting).\n"
                  + " Expected:\n" + _infoSightings + "\n"
                  + " Found:\n" + _existingSightings);
        }
        final HashMap<String, TableInfo.Column> _columnsTrips = new HashMap<String, TableInfo.Column>(4);
        _columnsTrips.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("startedAtMillis", new TableInfo.Column("startedAtMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("endedAtMillis", new TableInfo.Column("endedAtMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrips = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrips = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrips = new TableInfo("trips", _columnsTrips, _foreignKeysTrips, _indicesTrips);
        final TableInfo _existingTrips = TableInfo.read(db, "trips");
        if (!_infoTrips.equals(_existingTrips)) {
          return new RoomOpenHelper.ValidationResult(false, "trips(com.cgnature.butterflytracker.data.Trip).\n"
                  + " Expected:\n" + _infoTrips + "\n"
                  + " Found:\n" + _existingTrips);
        }
        final HashMap<String, TableInfo.Column> _columnsRoutePoints = new HashMap<String, TableInfo.Column>(5);
        _columnsRoutePoints.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutePoints.put("tripId", new TableInfo.Column("tripId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutePoints.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutePoints.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutePoints.put("timestampMillis", new TableInfo.Column("timestampMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutePoints = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutePoints = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutePoints = new TableInfo("route_points", _columnsRoutePoints, _foreignKeysRoutePoints, _indicesRoutePoints);
        final TableInfo _existingRoutePoints = TableInfo.read(db, "route_points");
        if (!_infoRoutePoints.equals(_existingRoutePoints)) {
          return new RoomOpenHelper.ValidationResult(false, "route_points(com.cgnature.butterflytracker.data.RoutePoint).\n"
                  + " Expected:\n" + _infoRoutePoints + "\n"
                  + " Found:\n" + _existingRoutePoints);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "77ecef34022afefef12816883c12d637", "8d195ba4c9dbfce67f06036e820e2fb9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "sightings","trips","route_points");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `sightings`");
      _db.execSQL("DELETE FROM `trips`");
      _db.execSQL("DELETE FROM `route_points`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SightingDao.class, SightingDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SightingDao sightingDao() {
    if (_sightingDao != null) {
      return _sightingDao;
    } else {
      synchronized(this) {
        if(_sightingDao == null) {
          _sightingDao = new SightingDao_Impl(this);
        }
        return _sightingDao;
      }
    }
  }
}
