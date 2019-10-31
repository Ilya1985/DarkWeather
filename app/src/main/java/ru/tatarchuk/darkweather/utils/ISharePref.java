package ru.tatarchuk.darkweather.utils;

import android.preference.PreferenceManager;

import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;

import static ru.tatarchuk.darkweather.utils.Const.CURRENT_LOCATION_ID;

public class ISharePref {

    public final static String UNITS_KEY = "key_units";
    public final static String LOCATION_KEY = "selected_location";
    public final static String NOTIFICATION_KEY = "notification_key";

    public static String getUnitsKey() {
        return PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .getString(UNITS_KEY, "metric");
    }

    public static void setUnitsKey(String unitsKey) {
        PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .edit()
                .putString(UNITS_KEY, unitsKey)
                .apply();
    }

    public static String getLocationId() {
        return PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .getString(LOCATION_KEY, CURRENT_LOCATION_ID);
    }

    public static void setLocationId(String string) {
        PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .edit()
                .putString(LOCATION_KEY, string)
                .apply();
    }

    public static void setNotification(Boolean isOn) {
        PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .edit()
                .putBoolean(NOTIFICATION_KEY, isOn)
                .apply();
    }

    public static Boolean getNotification() {
        return PreferenceManager
                .getDefaultSharedPreferences(WeatherApp.getInstance())
                .getBoolean(NOTIFICATION_KEY, false);
    }
}
