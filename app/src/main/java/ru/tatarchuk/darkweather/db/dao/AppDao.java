package ru.tatarchuk.darkweather.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverters;

import org.intellij.lang.annotations.Flow;
import org.threeten.bp.LocalDateTime;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.tatarchuk.darkweather.db.model.CurrentDetailElement;
import ru.tatarchuk.darkweather.db.model.CurrentlyElement;
import ru.tatarchuk.darkweather.db.model.DailyElement;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.db.model.FullTransaction;
import ru.tatarchuk.darkweather.db.model.HourlyElement;
import ru.tatarchuk.darkweather.db.model.LocAddress;
import ru.tatarchuk.darkweather.db.type_converters.TimeConverter;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Currently;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.DailyDatum;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.HourlyDatum;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationItem;

@Dao
public abstract class AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertHourly(List<HourlyDatum> hourlyData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDaily(List<DailyDatum> dailyData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertCurrently(Currently currently);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLocation(DataLocation location);

    @Query("DELETE FROM DataLocation WHERE mId = :id")
    public abstract void delLoc(String id);

    @Query("DELETE FROM HourlyDatum WHERE mLocationId = :id")
    public abstract void deleteHourly(String id);

    @Query("DELETE FROM DailyDatum WHERE mLocationId = :id")
    public abstract void deleteDaily(String id);

    @Query("DELETE FROM currently WHERE mLocationId = :id")
    public abstract void deleteCurrently(String id);

    @Query("SELECT mIcon, mSummary, mTemperature, mApparentTemperature FROM Currently WHERE mLocationId = :id")
    public abstract Flowable<List<CurrentlyElement>> getCurrentlyById(String id);

    @Query("SELECT mTime, mIcon, mTemperature FROM HourlyDatum WHERE mLocationId = :id")
    public abstract Flowable<List<HourlyElement>> getHourlyById(String id);

    @Query("SELECT mTime, mTemperatureMin, mTemperatureMax, mIcon FROM dailydatum WHERE mLocationId = :id AND mTime >= :time")
    public abstract Flowable<List<DailyElement>> getDailyById(String id, long time);

    @Query("SELECT mPressure, mHumidity, mWindSpeed, mWindBearing FROM currently WHERE mLocationId = :id")
    public abstract Flowable<List<CurrentDetailElement>> getCurrentDetailById(String id);

    @Query("SELECT mTime, mIcon, mTemperature FROM hourlydatum WHERE mLocationId = :id AND mTime >= :time ORDER BY mTime LIMIT 24")
    public abstract Flowable<List<HourlyElement>> getNext24(String id, Long time);

    @Query("SELECT * FROM datalocation WHERE mId = :id")
    public abstract Single<DataLocation> getLocation(String id);

    @Query("SELECT mLocationId, mFirstName, mSecondName, mTemperature, mIcon FROM DataLocation, Currently WHERE mId == mLocationId")
    public abstract Flowable<List<LocationItem>> getLocations();

    @Query("SELECT mFirstName, mSecondName FROM datalocation WHERE mId = :id")
    public abstract Flowable<List<LocAddress>> getAddress(String id);

    @Query("SELECT * FROM hourlydatum WHERE mLocationId = :id AND mTime >= :date1 AND mTime <= :date2")
    public abstract Flowable<List<HourlyDatum>> getHourlyByDate(String id, long date1, long date2);

    @Query("SELECT mTime FROM dailydatum WHERE mLocationId = :id AND mTime >= :time")
    abstract public @TypeConverters({TimeConverter.class})
    Single<List<LocalDateTime>> getDates(String id, @TypeConverters(TimeConverter.class) LocalDateTime time);

    @Query("SELECT * FROM datalocation")
    public abstract Single<List<DataLocation>> getSingleLocartions();

    @Transaction
    public void deleteLocation(String id) {
        delLoc(id);
        deleteDaily(id);
        deleteCurrently(id);
        deleteHourly(id);
    }

    @Transaction
    public void addLocation(FullTransaction transaction) {
        delLoc(transaction.getLocation().getId());
        insertLocation(transaction.getLocation());
        insertCurrently(transaction.getWeather().getCurrently());
        insertDaily(transaction.getWeather().getDaily().getData());
        insertHourly(transaction.getWeather().getHourly().getData());
    }

    @Transaction
    public void updateWeather(Weather weather) {
        deleteCurrently(weather.getCurrently().getLocationId());
        deleteHourly(weather.getCurrently().getLocationId());
        deleteDaily(weather.getCurrently().getLocationId());
        insertCurrently(weather.getCurrently());
        insertDaily(weather.getDaily().getData());
        insertHourly(weather.getHourly().getData());
    }
}
