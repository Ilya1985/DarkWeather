package ru.tatarchuk.darkweather.utils;

public class UnitConverter {

    public static String hPaToMmHg(Double hPa) {
        return hPa == null ? null : AppNumberFormatter.round(hPa * 0.750062);
    }
}
