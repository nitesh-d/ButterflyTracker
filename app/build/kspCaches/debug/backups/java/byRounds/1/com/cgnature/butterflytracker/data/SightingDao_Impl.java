package com.cgnature.butterflytracker.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SightingDao_Impl implements SightingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Sighting> __insertionAdapterOfSighting;

  private final EntityInsertionAdapter<Trip> __insertionAdapterOfTrip;

  private final EntityInsertionAdapter<RoutePoint> __insertionAdapterOfRoutePoint;

  private final EntityDeletionOrUpdateAdapter<Trip> __updateAdapterOfTrip;

  public SightingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSighting = new EntityInsertionAdapter<Sighting>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `sightings` (`id`,`category`,`commonName`,`scientificName`,`family`,`latitude`,`longitude`,`accuracyMeters`,`timestampMillis`,`note`,`tripId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Sighting entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCategory());
        statement.bindString(3, entity.getCommonName());
        statement.bindString(4, entity.getScientificName());
        statement.bindString(5, entity.getFamily());
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
        statement.bindDouble(8, entity.getAccuracyMeters());
        statement.bindLong(9, entity.getTimestampMillis());
        statement.bindString(10, entity.getNote());
        statement.bindLong(11, entity.getTripId());
      }
    };
    this.__insertionAdapterOfTrip = new EntityInsertionAdapter<Trip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `trips` (`id`,`startedAtMillis`,`endedAtMillis`,`name`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Trip entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getStartedAtMillis());
        if (entity.getEndedAtMillis() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getEndedAtMillis());
        }
        statement.bindString(4, entity.getName());
      }
    };
    this.__insertionAdapterOfRoutePoint = new EntityInsertionAdapter<RoutePoint>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `route_points` (`id`,`tripId`,`latitude`,`longitude`,`timestampMillis`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoutePoint entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTripId());
        statement.bindDouble(3, entity.getLatitude());
        statement.bindDouble(4, entity.getLongitude());
        statement.bindLong(5, entity.getTimestampMillis());
      }
    };
    this.__updateAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `trips` SET `id` = ?,`startedAtMillis` = ?,`endedAtMillis` = ?,`name` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Trip entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getStartedAtMillis());
        if (entity.getEndedAtMillis() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getEndedAtMillis());
        }
        statement.bindString(4, entity.getName());
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public Object insertSighting(final Sighting sighting,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSighting.insertAndReturnId(sighting);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTrip(final Trip trip, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTrip.insertAndReturnId(trip);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRoutePoint(final RoutePoint point,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRoutePoint.insertAndReturnId(point);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTrip(final Trip trip, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTrip.handle(trip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Sighting>> getAllSightings() {
    final String _sql = "SELECT * FROM sightings ORDER BY timestampMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sightings"}, new Callable<List<Sighting>>() {
      @Override
      @NonNull
      public List<Sighting> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCommonName = CursorUtil.getColumnIndexOrThrow(_cursor, "commonName");
          final int _cursorIndexOfScientificName = CursorUtil.getColumnIndexOrThrow(_cursor, "scientificName");
          final int _cursorIndexOfFamily = CursorUtil.getColumnIndexOrThrow(_cursor, "family");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMillis");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "tripId");
          final List<Sighting> _result = new ArrayList<Sighting>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Sighting _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCommonName;
            _tmpCommonName = _cursor.getString(_cursorIndexOfCommonName);
            final String _tmpScientificName;
            _tmpScientificName = _cursor.getString(_cursorIndexOfScientificName);
            final String _tmpFamily;
            _tmpFamily = _cursor.getString(_cursorIndexOfFamily);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final float _tmpAccuracyMeters;
            _tmpAccuracyMeters = _cursor.getFloat(_cursorIndexOfAccuracyMeters);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpTripId;
            _tmpTripId = _cursor.getLong(_cursorIndexOfTripId);
            _item = new Sighting(_tmpId,_tmpCategory,_tmpCommonName,_tmpScientificName,_tmpFamily,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpTimestampMillis,_tmpNote,_tmpTripId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Sighting>> getSightingsByCategory(final String category) {
    final String _sql = "SELECT * FROM sightings WHERE category = ? ORDER BY timestampMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sightings"}, new Callable<List<Sighting>>() {
      @Override
      @NonNull
      public List<Sighting> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCommonName = CursorUtil.getColumnIndexOrThrow(_cursor, "commonName");
          final int _cursorIndexOfScientificName = CursorUtil.getColumnIndexOrThrow(_cursor, "scientificName");
          final int _cursorIndexOfFamily = CursorUtil.getColumnIndexOrThrow(_cursor, "family");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMillis");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "tripId");
          final List<Sighting> _result = new ArrayList<Sighting>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Sighting _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCommonName;
            _tmpCommonName = _cursor.getString(_cursorIndexOfCommonName);
            final String _tmpScientificName;
            _tmpScientificName = _cursor.getString(_cursorIndexOfScientificName);
            final String _tmpFamily;
            _tmpFamily = _cursor.getString(_cursorIndexOfFamily);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final float _tmpAccuracyMeters;
            _tmpAccuracyMeters = _cursor.getFloat(_cursorIndexOfAccuracyMeters);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpTripId;
            _tmpTripId = _cursor.getLong(_cursorIndexOfTripId);
            _item = new Sighting(_tmpId,_tmpCategory,_tmpCommonName,_tmpScientificName,_tmpFamily,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpTimestampMillis,_tmpNote,_tmpTripId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Sighting>> getSightingsForTrip(final long tripId) {
    final String _sql = "SELECT * FROM sightings WHERE tripId = ? ORDER BY timestampMillis ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tripId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sightings"}, new Callable<List<Sighting>>() {
      @Override
      @NonNull
      public List<Sighting> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfCommonName = CursorUtil.getColumnIndexOrThrow(_cursor, "commonName");
          final int _cursorIndexOfScientificName = CursorUtil.getColumnIndexOrThrow(_cursor, "scientificName");
          final int _cursorIndexOfFamily = CursorUtil.getColumnIndexOrThrow(_cursor, "family");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMillis");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "tripId");
          final List<Sighting> _result = new ArrayList<Sighting>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Sighting _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpCommonName;
            _tmpCommonName = _cursor.getString(_cursorIndexOfCommonName);
            final String _tmpScientificName;
            _tmpScientificName = _cursor.getString(_cursorIndexOfScientificName);
            final String _tmpFamily;
            _tmpFamily = _cursor.getString(_cursorIndexOfFamily);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final float _tmpAccuracyMeters;
            _tmpAccuracyMeters = _cursor.getFloat(_cursorIndexOfAccuracyMeters);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final long _tmpTripId;
            _tmpTripId = _cursor.getLong(_cursorIndexOfTripId);
            _item = new Sighting(_tmpId,_tmpCategory,_tmpCommonName,_tmpScientificName,_tmpFamily,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpTimestampMillis,_tmpNote,_tmpTripId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getDistinctSpeciesCount() {
    final String _sql = "SELECT COUNT(DISTINCT commonName) FROM sightings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sightings"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getDistinctSpeciesCountByCategory(final String category) {
    final String _sql = "SELECT COUNT(DISTINCT commonName) FROM sightings WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sightings"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Trip>> getAllTrips() {
    final String _sql = "SELECT * FROM trips ORDER BY startedAtMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<Trip>>() {
      @Override
      @NonNull
      public List<Trip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAtMillis");
          final int _cursorIndexOfEndedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endedAtMillis");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Trip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpStartedAtMillis;
            _tmpStartedAtMillis = _cursor.getLong(_cursorIndexOfStartedAtMillis);
            final Long _tmpEndedAtMillis;
            if (_cursor.isNull(_cursorIndexOfEndedAtMillis)) {
              _tmpEndedAtMillis = null;
            } else {
              _tmpEndedAtMillis = _cursor.getLong(_cursorIndexOfEndedAtMillis);
            }
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new Trip(_tmpId,_tmpStartedAtMillis,_tmpEndedAtMillis,_tmpName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTrip(final long tripId, final Continuation<? super Trip> $completion) {
    final String _sql = "SELECT * FROM trips WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tripId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Trip>() {
      @Override
      @Nullable
      public Trip call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAtMillis");
          final int _cursorIndexOfEndedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endedAtMillis");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final Trip _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpStartedAtMillis;
            _tmpStartedAtMillis = _cursor.getLong(_cursorIndexOfStartedAtMillis);
            final Long _tmpEndedAtMillis;
            if (_cursor.isNull(_cursorIndexOfEndedAtMillis)) {
              _tmpEndedAtMillis = null;
            } else {
              _tmpEndedAtMillis = _cursor.getLong(_cursorIndexOfEndedAtMillis);
            }
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result = new Trip(_tmpId,_tmpStartedAtMillis,_tmpEndedAtMillis,_tmpName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RoutePoint>> getRouteForTrip(final long tripId) {
    final String _sql = "SELECT * FROM route_points WHERE tripId = ? ORDER BY timestampMillis ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tripId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"route_points"}, new Callable<List<RoutePoint>>() {
      @Override
      @NonNull
      public List<RoutePoint> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "tripId");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfTimestampMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMillis");
          final List<RoutePoint> _result = new ArrayList<RoutePoint>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoutePoint _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTripId;
            _tmpTripId = _cursor.getLong(_cursorIndexOfTripId);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final long _tmpTimestampMillis;
            _tmpTimestampMillis = _cursor.getLong(_cursorIndexOfTimestampMillis);
            _item = new RoutePoint(_tmpId,_tmpTripId,_tmpLatitude,_tmpLongitude,_tmpTimestampMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
