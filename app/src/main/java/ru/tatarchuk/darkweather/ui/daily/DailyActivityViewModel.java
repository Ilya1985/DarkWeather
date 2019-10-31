package ru.tatarchuk.darkweather.ui.daily;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.repo.DbModel;

public class DailyActivityViewModel extends ViewModel {

    @Inject
    DbModel mDataBase;

    private Disposable mDsp;

    private MutableLiveData<List<LocalDateTime>> mDates = new MutableLiveData<>();

    public DailyActivityViewModel() {
        WeatherApp.getAppComponent().inject(this);
        mDsp = mDataBase.getDates().subscribe(new Consumer<List<LocalDateTime>>() {
            @Override
            public void accept(List<LocalDateTime> localDateTimes) throws Exception {
                mDates.postValue(localDateTimes);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDsp != null) mDsp.dispose();
    }
}
