package ru.tatarchuk.darkweather.ui.main.navigation;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.db.model.FullTransaction;
import ru.tatarchuk.darkweather.repo.DbModel;
import ru.tatarchuk.darkweather.repo.RestModel;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationItem;
import ru.tatarchuk.darkweather.utils.ISharePref;

import static ru.tatarchuk.darkweather.utils.ISharePref.LOCATION_KEY;

public class NavigationViewModel extends ViewModel {

    private static final String TAG = "Weather " + NavigationViewModel.class.getSimpleName();

    @Inject
    DbModel mDatabase;

    @Inject
    RestModel mRestModel;

    private Disposable mSearchDsp;
    private Disposable mLocationDsp;
    private CompositeDisposable mDisposables;

    private MutableLiveData<Boolean> mLoadState = new MutableLiveData<>(false);
    private MutableLiveData<List<? extends BaseItem>> mSearchResult = new MutableLiveData<>();
    private List<FullTransaction> mItems = new ArrayList<>();
    private MutableLiveData<List<LocationItem>> mLocations = new MutableLiveData<>();

    public NavigationViewModel() {
        WeatherApp.getAppComponent().inject(this);
        mDisposables = new CompositeDisposable();
        getDbLocations();
    }

    public void search(String input) {
        mSearchDsp = mRestModel.search(input)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLoadState.postValue(true);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mLoadState.postValue(false);
                    }
                })
                .subscribe(this::addResult, e -> Toast.makeText(WeatherApp.getInstance(), "Search Error ", Toast.LENGTH_SHORT).show());
    }

    public void disposeSearch() {
        if (mSearchResult.getValue() != null)
            mSearchResult.getValue().clear();
        if (mSearchDsp != null) mSearchDsp.dispose();
    }

    public MutableLiveData<List<? extends BaseItem>> getSearchResult() {
        return mSearchResult;
    }

    public MutableLiveData<Boolean> getLoadState() {
        return mLoadState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposeSearch();
        mLocationDsp.dispose();
        mDisposables.dispose();
    }

    private void addResult(FullTransaction item) {
        mItems.add(item);
        mSearchResult.postValue(mItems);
    }

    public void clearSearchResult() {
        mItems.clear();
        mSearchResult.postValue(mItems);
    }

    public void addLocation(int position) {
        mDatabase.addLocation(mItems.get(position));
        ISharePref.setLocationId(mItems.get(position).getLocation().getId());
    }

    public void deleteLocation(int position) {
        if (mLocations.getValue() != null) {
            String id = mLocations.getValue().get(position).getId();
            if (id.equals(ISharePref.getLocationId())) {
                mDisposables.add(mDatabase.getSingleLocations()
                        .subscribe(dataLocations -> {
                            if (dataLocations.size() != 0)
                                ISharePref.setLocationId(dataLocations.get(0).getId());
                        }));
            }
            mDatabase.deleteLocation(mLocations.getValue().get(position).getId());
        }
    }

    public MutableLiveData<List<LocationItem>> getLocations() {
        return mLocations;
    }

    public void getDbLocations() {
        if (mLocationDsp != null) mLocationDsp.dispose();
        mLocationDsp = mDatabase.getLocations()
                .subscribe(locationItems -> mLocations.postValue(locationItems));
    }

    public void setLocation(Integer position) {
        if (mLocations.getValue() != null) {
            String id = mLocations.getValue().get(position).getId();
            if (!ISharePref.getLocationId().equals(id))
                ISharePref.setLocationId(id);
        }
    }

    public void preferenceChanged(String key) {
        if (key.equals(LOCATION_KEY))
            getDbLocations();
    }
}
