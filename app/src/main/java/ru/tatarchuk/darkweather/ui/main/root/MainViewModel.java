package ru.tatarchuk.darkweather.ui.main.root;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.db.model.FullTransaction;
import ru.tatarchuk.darkweather.db.model.LocAddress;
import ru.tatarchuk.darkweather.repo.DbModel;
import ru.tatarchuk.darkweather.repo.RestModel;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.utils.ISharePref;

import static ru.tatarchuk.darkweather.utils.ISharePref.LOCATION_KEY;

public class MainViewModel extends ViewModel {

    private final String TAG = "Weather " + MainViewModel.class.getSimpleName();

    @Inject
    RestModel mRestModel;

    @Inject
    DbModel mDataBase;

    private CompositeDisposable mDisposables;

    private Disposable mWeatherDsp;
    private Disposable mAddressDsp;

    private MutableLiveData<List<? extends BaseItem>> mItems = new MutableLiveData<>();
    private MutableLiveData<LocAddress> mAddress = new MutableLiveData<>(new LocAddress());
    private MutableLiveData<Boolean> loadState = new MutableLiveData<>(false);

    public MainViewModel() {
        WeatherApp.getAppComponent().inject(this);
        mDisposables = new CompositeDisposable();
        getDbData(ISharePref.getLocationId());
        //loadData(55.981430, 37.200197);
        getDbAddress();
    }

    public void getDbData(String id) {
        if (mWeatherDsp != null) mWeatherDsp.dispose();
        mWeatherDsp = mDataBase.getDailyForecast(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> mItems.setValue(items));
    }

    MutableLiveData<List<? extends BaseItem>> getItems() {
        return mItems;
    }

    public MutableLiveData<Boolean> getLoadState() {
        return loadState;
    }

    public MutableLiveData<LocAddress> getAddress() {
        return mAddress;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mWeatherDsp != null) mWeatherDsp.dispose();
        if (mAddressDsp != null) mAddressDsp.dispose();
    }
/*
    public void loadData(double lat, double lon) {
        mDisposables.add(
                Observable.zip(mRestModel.loadAddress(lat, lon), mRestModel.loadWeather(lat, lon), (responseAddress, weather) ->
                        new FullTransaction(CURRENT_LOCATION_ID, Utils.createAddress(responseAddress), lat, lon, weather))
                        .doFinally(() -> loadState.postValue(false))
                        .subscribe(transaction ->  mDataBase.addLocation(transaction), e -> Log.i(TAG, "error ", e)));
    }*/

    public void refresh() {
        String id = ISharePref.getLocationId();
        mDisposables.add(mDataBase.getLocation(id)
                .toObservable()
                .flatMap((Function<DataLocation, Observable<Weather>>) dataLocation -> mRestModel.loadWeather(dataLocation.getLat(), dataLocation.getLon()))
                .doFinally(() -> loadState.postValue(false))
                .doOnNext(weather -> weather.setId(id))
                .subscribe(weather -> mDataBase.updateWeather(weather), e -> {
                    Log.i(TAG, "update error ", e);
                    Toast.makeText(WeatherApp.getInstance(), "Failed update", Toast.LENGTH_SHORT).show();
                }));
    }

    public void refreshAll() {
        mDisposables.add(mDataBase.getSingleLocations()
                .doOnSubscribe(disposable -> loadState.postValue(true))
                .toObservable()
                .flatMap(Observable::fromIterable)
                .flatMap(new Function<DataLocation, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> apply(DataLocation dataLocation) throws Exception {
                        return mRestModel.loadWeather(dataLocation.getLat(), dataLocation.getLon());
                    }
                }, new BiFunction<DataLocation, Weather, FullTransaction>() {
                    @Override
                    public FullTransaction apply(DataLocation dataLocation, Weather weather) throws Exception {
                        return new FullTransaction(dataLocation, weather);
                    }
                })
                .doFinally(() -> loadState.postValue(false))
                .subscribe(tr -> mDataBase.addLocation(tr), e -> {
                    Log.i(TAG, "Update failed", e);
                    Toast.makeText(WeatherApp.getInstance(), "Update failed", Toast.LENGTH_SHORT).show();
                }));
    }

    public void preferenceChanged(String key) {
        if (key.equals(LOCATION_KEY)) {
            getDbAddress();
            getDbData(ISharePref.getLocationId());
        }

    }

    public void getDbAddress() {
        if (mAddressDsp != null) mAddressDsp.dispose();
        mAddressDsp = mDataBase.getAddress(ISharePref.getLocationId()).subscribe(locAddresses -> {
            if (!locAddresses.isEmpty())
                mAddress.postValue(locAddresses.get(0));
            else
                mAddress.postValue(new LocAddress());
        });
    }
}
