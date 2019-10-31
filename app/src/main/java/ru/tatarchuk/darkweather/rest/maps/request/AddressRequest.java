package ru.tatarchuk.darkweather.rest.maps.request;

import java.util.Map;

public class AddressRequest extends MapsRequest {

    private final Double mLat;

    private final Double mLng;

    private String mFilter = "APPROXIMATE";

    public AddressRequest(Double lat, Double lng) {
        mLat = lat;
        mLng = lng;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        map.put("latlng", mLat + "," + mLng);
        map.put("location_type", mFilter);
    }
}
