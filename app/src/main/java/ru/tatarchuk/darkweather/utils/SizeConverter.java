package ru.tatarchuk.darkweather.utils;

import android.util.TypedValue;

import ru.tatarchuk.darkweather.WeatherApp;

public class SizeConverter {

    public static int dpToPx(float dp) {
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, WeatherApp
                        .getInstance()
                        .getResources()
                        .getDisplayMetrics());
    }

    public static int spToPx(float sp) {
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, WeatherApp
                        .getInstance()
                        .getResources()
                        .getDisplayMetrics());
    }

    public static int dpToSp(float dp) {
        return (int) (dpToPx(dp) / WeatherApp.getInstance()
                .getResources()
                .getDisplayMetrics()
                .scaledDensity);
    }
}
