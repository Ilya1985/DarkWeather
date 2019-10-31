package ru.tatarchuk.darkweather.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import org.jetbrains.annotations.NotNull;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.WeatherApp;

public class DrawableUtils {

    @DrawableRes
    public static int getDrawableResId(String name) {
        Context context = WeatherApp.getInstance();
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static Drawable getDrawable(String name) {
        Context context = WeatherApp.getInstance();
        try {
            return AppCompatResources.getDrawable(context, getDrawableResId(name));
        } catch (Resources.NotFoundException e) {
            Log.e("12345_", "Not Found Drawable " + name);
            return AppCompatResources.getDrawable(context, R.drawable.unknown);
        }
    }

    public static void animate(@NotNull ImageView view) {
        animate(view.getDrawable());
    }

    public static void animate(Drawable d) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && d instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        } else if (d instanceof AnimatedVectorDrawableCompat) {
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }
    }
}
