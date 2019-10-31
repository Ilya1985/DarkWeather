package ru.tatarchuk.darkweather.db.type_converters;

import androidx.room.TypeConverter;

import java.text.DecimalFormat;

public class DegConverter {

    @TypeConverter
    public String intToDag(Integer deg) {
        return deg == null ? "" : new DecimalFormat("+0;-0").format(deg);
    }
}
