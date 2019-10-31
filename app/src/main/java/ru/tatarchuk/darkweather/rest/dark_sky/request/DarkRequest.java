package ru.tatarchuk.darkweather.rest.dark_sky.request;

import java.util.Locale;
import java.util.Map;

import ru.tatarchuk.darkweather.rest.base.BaseRequest;

public class DarkRequest extends BaseRequest {

    public static final String sKey = "71bb6e1a1684bff64e04bf9b0edb98d1";

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("extend", "hourly ");
        map.put("exclude", "alerts,flags");
        map.put("lang", Locale.getDefault().getLanguage().equals("ru") ? "ru" : "en");
        map.put("units", "si");
    }
}
