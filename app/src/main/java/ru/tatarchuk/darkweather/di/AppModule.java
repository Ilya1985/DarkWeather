package ru.tatarchuk.darkweather.di;

import android.content.Context;

import androidx.room.Room;

import com.google.android.gms.location.FusedLocationProviderClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.db.dao.AppDao;
import ru.tatarchuk.darkweather.db.AppDataBase;
import ru.tatarchuk.darkweather.repo.DbModel;
import ru.tatarchuk.darkweather.repo.LocationModel;
import ru.tatarchuk.darkweather.repo.RestModel;

@Module
public class AppModule {

    public WeatherApp mWeatherApp;

    public AppModule(WeatherApp app) {
        mWeatherApp = app;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mWeatherApp;
    }

    @Singleton
    @Provides
    RestModel provideRestModel() {
        return new RestModel();
    }

    @Singleton
    @Provides
    AppDataBase provideDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "database").build();
    }

    @Singleton
    @Provides
    AppDao provideAppDao(AppDataBase dataBase) {
        return dataBase.getDao();
    }

    @Singleton
    @Provides
    DbModel provideDb() {
        return new DbModel();
    }

    @Provides
    @Singleton
    FusedLocationProviderClient provideLocationClient(Context context) {
        return new FusedLocationProviderClient(context);
    }

    @Provides
    @Singleton
    LocationModel provideLocationModel() {
        return new LocationModel();
    }
}
