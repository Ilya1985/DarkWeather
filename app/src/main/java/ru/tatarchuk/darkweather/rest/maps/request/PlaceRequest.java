package ru.tatarchuk.darkweather.rest.maps.request;

import java.util.Map;

public class PlaceRequest extends MapsRequest {

    private final String mType = "(cities)";
    private String mInput;

    public PlaceRequest(String input) {
        mInput = input;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        map.put("types", mType);
        map.put("input", mInput);
    }
}
