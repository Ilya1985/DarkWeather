package ru.tatarchuk.darkweather.rest.dark_sky;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.tatarchuk.darkweather.rest.base.RestClient;

public class DarkSkyClient extends RestClient {

    @Override
    public Gson createGson() {
        return new GsonBuilder().create();
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.darksky.net/";
    }
}
