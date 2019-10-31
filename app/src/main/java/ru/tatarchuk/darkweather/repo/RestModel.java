package ru.tatarchuk.darkweather.repo;

import androidx.core.util.Pair;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.db.model.FullTransaction;
import ru.tatarchuk.darkweather.rest.dark_sky.DarkSkyApi;
import ru.tatarchuk.darkweather.rest.dark_sky.request.DarkRequest;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.rest.maps.GoogleMapsApi;
import ru.tatarchuk.darkweather.rest.maps.request.AddressRequest;
import ru.tatarchuk.darkweather.rest.maps.request.DetailsRequest;
import ru.tatarchuk.darkweather.rest.maps.request.PlaceRequest;
import ru.tatarchuk.darkweather.rest.maps.responce.Prediction;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseAddress;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseDetails;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponsePlace;
import ru.tatarchuk.darkweather.utils.Utils;

import static ru.tatarchuk.darkweather.utils.Const.CURRENT_LOCATION_ID;

public class RestModel {

    @Inject
    GoogleMapsApi mMapsApi;

    @Inject
    DarkSkyApi mDarkSkyApi;

    public RestModel() {
        WeatherApp.getAppComponent().inject(this);
    }

    public Observable<ResponsePlace> loadPlace(String input) {
        return mMapsApi
                .getPlaces(new PlaceRequest(input).toMap())
                .subscribeOn(Schedulers.io());
    }

    public Observable<Weather> loadWeather(double lat, double lon) {
        return mDarkSkyApi
                .getForecast(DarkRequest.sKey, lat, lon, new DarkRequest().toMap())
                .subscribeOn(Schedulers.io());
    }

    public Observable<ResponseAddress> loadAddress(double lat, double lon) {
        return mMapsApi.getAddress(new AddressRequest(lat, lon).toMap())
                .subscribeOn(Schedulers.io());
    }

    public Observable<FullTransaction> loadCurrentLocation(double lat, double lon) {
        return Observable.zip(loadAddress(lat, lon),
                loadWeather(lat, lon), (responseAddress, weather) ->
                        new FullTransaction(CURRENT_LOCATION_ID, Utils.createAddress(responseAddress), lat, lon, weather))
                .subscribeOn(Schedulers.io());
    }

    public Observable<FullTransaction> loadCurrentLoc(double lat, double lng) {
        return Observable.zip(loadAddress(lat, lng), loadWeather(lat, lng), new BiFunction<ResponseAddress, Weather, FullTransaction>() {
            @Override
            public FullTransaction apply(ResponseAddress responseAddress, Weather weather) throws Exception {
                Pair<String, String> address = Utils.createAddress(responseAddress);
                DataLocation location = new DataLocation(CURRENT_LOCATION_ID, address.first, address.second, lat, lng);
                return new FullTransaction(location, weather);
            }
        });
    }

    public Observable<FullTransaction> search(String input) {
        return mMapsApi.getPlaces(new PlaceRequest(input).toMap())
                .subscribeOn(Schedulers.io())
                // Получает id населенного пункта
                .switchMap(new Function<ResponsePlace, ObservableSource<? extends Prediction>>() {
                    @Override
                    public ObservableSource<? extends Prediction> apply(ResponsePlace responsePlace) throws Exception {
                        return Observable.fromIterable(responsePlace.getPredictions());
                    }
                // Поулчает детали (название)
                }).flatMap(new Function<Prediction, ObservableSource<ResponseDetails>>() {
                    @Override
                    public ObservableSource<ResponseDetails> apply(Prediction prediction) throws Exception {
                        return mMapsApi.getDetails(new DetailsRequest(prediction.getPlaceId()).toMap());
                    }
                // Сохраняет полченные данные в DataLocation
                }, new BiFunction<Prediction, ResponseDetails, DataLocation>() {
                    @Override
                    public DataLocation apply(Prediction prediction, ResponseDetails responseDetails) throws Exception {
                        return new DataLocation(prediction, responseDetails);
                    }
                 // Запрос погоды
                }).flatMap(new Function<DataLocation, ObservableSource<Weather>>() {
                    @Override
                    public ObservableSource<Weather> apply(DataLocation dataLocation) throws Exception {
                        return mDarkSkyApi.getForecast(DarkRequest.sKey, dataLocation.getLat(), dataLocation.getLon(), new DarkRequest().toMap());
                    }
                // Собирает все данные в объек FullTransaction
                }, new BiFunction<DataLocation, Weather, FullTransaction>() {

                    @Override
                    public FullTransaction apply(DataLocation dataLocation, Weather weather) throws Exception {
                        return new FullTransaction(dataLocation, weather);
                    }
                // При неудачной загрузке повторяет попытку трижды с периодичностью в одну секунду
                }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.take(3).delay(1, TimeUnit.SECONDS);
                    }
                });
    }
}
