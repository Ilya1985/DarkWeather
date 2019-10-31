package ru.tatarchuk.darkweather.db.type_converters;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

public class TimeConverter {

    @TypeConverter
    public LocalDateTime longToTime(Long time) {
        return time == null ? null : LocalDateTime
                .ofEpochSecond(time, 0, ZoneOffset.UTC);
    }

    @TypeConverter
    public Long TimeToLong(LocalDateTime time) {
        return time.toEpochSecond(OffsetDateTime.now().getOffset());
    }
}
