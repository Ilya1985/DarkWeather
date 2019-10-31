package ru.tatarchuk.darkweather.rest.maps.request;

import java.util.Locale;
import java.util.Map;

import ru.tatarchuk.darkweather.rest.base.BaseRequest;

public class MapsRequest extends BaseRequest {

    private final static String mKey = "AIzaSyCTl9bkuTgfolWHFzJR_V1SDrzHDxU_cL8";
    private final static String InvalideKey = "AIzaSyCTl9bkuTgfolWHFzJR_V1SDrzHDxU";
    private final String mLang;

    public MapsRequest() {
        mLang =  Locale.getDefault().getLanguage().equals("ru") ? "ru" : "en";
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put("key", mKey);
        map.put("language", mLang);
    }
}
