package ru.tatarchuk.darkweather.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.tatarchuk.darkweather.db.dao.AppDao;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Currently;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.DailyDatum;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.HourlyDatum;

@Database(entities = {DailyDatum.class, HourlyDatum.class, DataLocation.class, Currently.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract AppDao getDao();
}
