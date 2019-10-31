package ru.tatarchuk.darkweather.rest.dark_sky;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;

public interface DarkSkyApi {

    @GET("forecast/{key}/{lat},{lon}")
    Observable<Weather> getForecast(@Path("key") String key,
                                    @Path("lat") double lat,
                                    @Path("lon") double lon,
                                    @QueryMap Map<String, String> queries);
}
