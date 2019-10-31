package ru.tatarchuk.darkweather.repo;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.db.dao.AppDao;
import ru.tatarchuk.darkweather.db.model.BaseElement;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.db.model.FullTransaction;
import ru.tatarchuk.darkweather.db.model.LocAddress;
import ru.tatarchuk.darkweather.rest.dark_sky.responce.Weather;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationItem;
import ru.tatarchuk.darkweather.ui.main.root.recycler.hourly.ListHourlyItem;
import ru.tatarchuk.darkweather.utils.ISharePref;

import static ru.tatarchuk.darkweather.utils.Const.CURRENT_LOCATION_ID;

public class DbModel {

    @Inject
    AppDao mDao;

    public DbModel() {
        WeatherApp.getAppComponent().inject(this);
    }

    public Flowable<List<? extends BaseItem>> getDailyForecast(String id) {
        return Flowable.zip(
                mDao.getCurrentlyById(id).flatMap(convert), getHourly(id), mDao.getDailyById(id, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                        .flatMap(convert), (currentlyItems, listHourlyItem, dailyItems) -> {
                    List<BaseItem> result = new ArrayList<>(currentlyItems);
                    result.add(listHourlyItem);
                    result.addAll(dailyItems);
                    return result;
                });
    }

    public Single<DataLocation> getLocation(String id) {
        return mDao.getLocation(id).subscribeOn(Schedulers.io());
    }

    public Flowable<List<LocAddress>> getAddress(String id) {
        return mDao.getAddress(id);
    }

    private Flowable<ListHourlyItem> getHourly(String id) {
        return Flowable.zip(
                mDao.getCurrentDetailById(id).flatMap(convert),
                mDao.getNext24(id, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)).flatMap(convert), (baseItems, baseItems2) -> {
                    List<BaseItem> result = new ArrayList<>();
                    result.addAll(baseItems);
                    result.addAll(baseItems2);
                    return new ListHourlyItem(result);
                });
    }

    private Function<List<? extends BaseElement>, Flowable<List<? extends BaseItem>>> convert = baseElements -> {
        List<BaseItem> result = new ArrayList<>();
        for (BaseElement element : baseElements)
            result.add(element.toItem());
        return Flowable.just(result);
    };

    public void addLocation(FullTransaction transaction) {
        Executors.newFixedThreadPool(4).submit(() -> mDao.addLocation(transaction));
    }

    public void updateWeather(Weather weather) {
        mDao.updateWeather(weather);
    }

    public Flowable<List<LocationItem>> getLocations() {
        return mDao.getLocations()
                .flatMap(new Function<List<LocationItem>, Flowable<List<LocationItem>>>() {
                    @Override
                    public Flowable<List<LocationItem>> apply(List<LocationItem> locationItems) throws Exception {
                        int current = -1;
                        for (int i = 0; i < locationItems.size(); i++) {
                            if (locationItems.get(i).getId().equals(CURRENT_LOCATION_ID)) {
                                current = i;
                                break;
                            }
                        }
                        if (current != -1) {
                            LocationItem item = locationItems.remove(current);
                            locationItems.add(0, item);
                        }
                        return Flowable.just(locationItems);
                    }
                });
    }

    public void deleteLocation(String id) {
        Executors.newFixedThreadPool(2).submit(() -> mDao.deleteLocation(id));
    }

    public Single<List<LocalDateTime>> getDates() {
        LocalDateTime time = LocalDateTime.now();
        return mDao.getDates(ISharePref.getLocationId(), time);
    }

    public Single<List<DataLocation>> getSingleLocations() {
        return mDao.getSingleLocartions().subscribeOn(Schedulers.io());
    }
}