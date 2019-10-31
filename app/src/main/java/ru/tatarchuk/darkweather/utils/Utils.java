package ru.tatarchuk.darkweather.utils;

import androidx.annotation.StringRes;
import androidx.core.util.Pair;

import java.lang.reflect.Field;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.rest.maps.responce.AddressComponent;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseAddress;
import ru.tatarchuk.darkweather.rest.maps.responce.Result;

public class Utils {

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String getAddressFirst(ResponseAddress response) {
        for (Result result : response.getResults()) {
            for (AddressComponent component : result.getAddressComponents()) {
                for (String type : component.getTypes()) {
                    if (type.equals("locality"))
                        return component.getShortName();
                }
            }
        }
        return "";
    }

    private static String getAddressSecond(ResponseAddress response) {
        String[] types = {"sublocality_level_2", "sublocality_level_1", "administrative_area_level_1"};
        for (String type : types) {
            for (Result result : response.getResults()) {
                for (AddressComponent component : result.getAddressComponents()) {
                    for (String compType : component.getTypes()) {
                        if (compType.equals(type))
                            return component.getShortName();
                    }
                }
            }
        }
        return "";
    }

    public static Pair<String, String> createAddress(ResponseAddress response) {
        return new Pair<>(getAddressFirst(response), getAddressSecond(response));
    }

    @StringRes
    public static Integer degToDir(Integer deg) {
        if (deg == null) return null;
        int direction = (int) Math.round(((double) deg % 360) / 45) % 8;
        switch (direction) {
            case 0:
                return R.string.north;
            case 1:
                return R.string.northeast;
            case 2:
                return R.string.east;
            case 3:
                return R.string.southeast;
            case 4:
                return R.string.south;
            case 5:
                return R.string.southwest;
            case 6:
                return R.string.west;
            case 7:
                return R.string.northwest;
            default:
                return -1;
        }
    }
}
