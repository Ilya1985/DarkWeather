package ru.tatarchuk.darkweather.utils;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

public class AppDateFormatter {

    private static DateTimeFormatter sTime24;
    private static DateTimeFormatter sTime12;
    private static DateTimeFormatter sDayOfWeek;
    private static DateTimeFormatter sDayAndMonth;
    private static DateTimeFormatter sDateTime;

    static {
        sTime24 = DateTimeFormatter.ofPattern("HH:mm");
        sTime12 = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        sDateTime = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        sDayAndMonth = DateTimeFormatter.ofPattern("dd MMMM");
        sDayOfWeek = DateTimeFormatter.ofPattern("EEEE");
    }

    public static DateTimeFormatter getTime24() {
        return sTime24;
    }

    public static DateTimeFormatter getTime12() {
        return sTime12;
    }

    public static DateTimeFormatter getDayOfWeek() {
        return sDayOfWeek;
    }

    public static DateTimeFormatter getDayAndMonth() {
        return sDayAndMonth;
    }

    public static DateTimeFormatter getDateTime() {
        return sDateTime;
    }
}
