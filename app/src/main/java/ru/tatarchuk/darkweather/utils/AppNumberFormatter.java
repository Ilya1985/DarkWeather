package ru.tatarchuk.darkweather.utils;

import java.text.DecimalFormat;
import java.util.Objects;

public class AppNumberFormatter {
    private AppNumberFormatter() {
    }

    private static final ThreadLocal<DecimalFormat> SIGNED;
    private static final ThreadLocal<DecimalFormat> UNSIGNED;

    static {
        SIGNED = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("+0;-0");
            }
        };

        UNSIGNED = new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                return new DecimalFormat("0");
            }
        };
    }

    public static String signed(Double d) {
        return d == null ? "" : Objects.requireNonNull(SIGNED.get()).format(d);
    }

    public static String doubleToDeg(Double d) {
        if (d == null)
            return "";
        String result = Math.round(d) == 0 ? "0" : Objects.requireNonNull(SIGNED.get()).format(d);
        return result + "\u00B0";
    }

    public static String round(Double d) {
        return d == null ? "" : Objects.requireNonNull(UNSIGNED.get()).format(d);
    }
}
