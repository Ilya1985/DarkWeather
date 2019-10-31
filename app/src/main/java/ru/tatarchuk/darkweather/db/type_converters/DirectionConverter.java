package ru.tatarchuk.darkweather.db.type_converters;

import androidx.room.TypeConverter;

import ru.tatarchuk.darkweather.utils.Utils;

public class DirectionConverter {

    @TypeConverter
    public Integer degToDirection(Integer deg) {
        return Utils.degToDir(deg);
    }
}
