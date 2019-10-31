package ru.tatarchuk.darkweather.rest.maps;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseAddress;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseDetails;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponsePlace;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseTimeZone;

public interface GoogleMapsApi {

    @GET("place/autocomplete/json?")
    Observable<ResponsePlace> getPlaces(@QueryMap Map<String, String> map);

    @GET("place/details/json?")
    Observable<ResponseDetails> getDetails(@QueryMap Map<String, String> map);

    @GET("geocode/json?")
    Observable<ResponseAddress> getAddress(@QueryMap Map<String, String> map);

    @GET("timezone/json?")
    Observable<ResponseTimeZone> getTimeZone(@QueryMap Map<String, String> map);
}
