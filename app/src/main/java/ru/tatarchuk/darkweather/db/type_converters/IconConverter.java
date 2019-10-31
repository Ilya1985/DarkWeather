package ru.tatarchuk.darkweather.db.type_converters;

import android.graphics.drawable.Drawable;

import androidx.room.TypeConverter;

import ru.tatarchuk.darkweather.utils.DrawableUtils;

public class IconConverter {

    @TypeConverter
    public Drawable nameToDrawable(String name) {
        return DrawableUtils.getDrawable(name);
    }
}
