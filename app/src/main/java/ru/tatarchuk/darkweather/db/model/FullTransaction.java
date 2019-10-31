package ru.tatarchuk.darkweather.db.model;


import android.graphics.drawable.Drawable;

import androidx.core.util.Pair;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.DailyDatum;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.HourlyDatum;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

public class FullTransaction implements BaseItem {

    private final String TAG = "Weather " + FullTransaction.class.getSimpleName();

    private DataLocation mLocation;
    private Weather mWeather;

    public FullTransaction(String id, Pair<String, String> address, double lat, double lon) {
        mLocation = new DataLocation(id, address.first, address.second, lat, lon);
    }

    public FullTransaction(String id, Pair<String, String> address, double lat, double lon, Weather weather) {
        this(id, address, lat, lon);
        mWeather = weather;
        for (HourlyDatum datum : mWeather.getHourly().getData()) {
            datum.setLocationId(id);
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }

        for (DailyDatum datum : mWeather.getDaily().getData()) {
            datum.setLocationId(id);
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }
        weather.getCurrently().setIcon(weather.getCurrently().getIcon().replace('-', '_'));
        mWeather.getCurrently().setLocationId(id);
    }

    public FullTransaction(DataLocation location, Weather weather) {
        mLocation = location;
        mWeather = weather;
        for (HourlyDatum datum : mWeather.getHourly().getData()) {
            datum.setLocationId(location.getId());
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }

        for (DailyDatum datum : mWeather.getDaily().getData()) {
            datum.setLocationId(location.getId());
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }
        weather.getCurrently().setIcon(weather.getCurrently().getIcon().replace('-', '_'));
        mWeather.getCurrently().setLocationId(location.getId());
    }

    public Weather getWeather() {
        return mWeather;
    }

    public void setWeather(Weather weather) {
        mWeather = weather;
    }

    public DataLocation getLocation() {
        return mLocation;
    }

    public void setLocation(DataLocation location) {
        mLocation = location;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_search;
    }

    public String getFirstName() {
        return mLocation.getFirstName();
    }

    public String getSecondName() {
        return mLocation.getSecondName();
    }

    public Drawable getIcon() {
        return DrawableUtils.getDrawable(mWeather.getCurrently().getIcon());
    }

    public String getTemp() {
        return AppNumberFormatter.doubleToDeg(mWeather.getCurrently().getTemperature());
    }
}
