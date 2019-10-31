package ru.tatarchuk.darkweather.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.NotNull;

import ru.tatarchuk.darkweather.WeatherApp;

public class SystemUtils {

    public static boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) WeatherApp.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void hideKeyboardFrom(@NotNull View view) {
        InputMethodManager imm = (InputMethodManager) WeatherApp.getInstance()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void vibrate(long millis) {
        Vibrator vibrator = (Vibrator) WeatherApp.getInstance()
                .getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(millis);
        }
    }

    public static Point getDisplayMetric(){
        WindowManager manager = (WindowManager) WeatherApp.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (manager != null) {
            display = manager.getDefaultDisplay();
        }
        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }
        return size;
    }
}
