package ru.tatarchuk.darkweather.rest.maps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.tatarchuk.darkweather.rest.base.RestClient;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseAddress;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseDetails;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponsePlace;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseTimeZone;

public class MapsRestClient extends RestClient {

    @Override
    protected String getBaseUrl() {
        return "https://maps.googleapis.com/maps/api/";
    }

    @Override
    public Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        MapDeserializer deserializer = new MapDeserializer<>();
        gsonBuilder.registerTypeAdapter(ResponseAddress.class, deserializer);
        gsonBuilder.registerTypeAdapter(ResponseDetails.class, deserializer);
        gsonBuilder.registerTypeAdapter(ResponsePlace.class, deserializer);
        gsonBuilder.registerTypeAdapter(ResponseTimeZone.class, deserializer);
        return gsonBuilder.create();
    }
}
