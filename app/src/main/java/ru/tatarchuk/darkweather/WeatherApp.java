package ru.tatarchuk.darkweather;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.jetbrains.annotations.Contract;

import ru.tatarchuk.darkweather.di.AppComponent;
import ru.tatarchuk.darkweather.di.AppModule;
import ru.tatarchuk.darkweather.di.DaggerAppComponent;

public class WeatherApp extends Application {

    private static WeatherApp sInstance;

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        sInstance = this;
        initComponent();
    }

    @Contract(pure = true)
    public static WeatherApp getInstance() {
        return sInstance;
    }

    private void initComponent() {
     /*   sAppComponent = DaggerAppComponent
                .builder()
                .createAppBuilder(this)
                .buildAppComponent();*/

            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Contract(pure = true)
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
