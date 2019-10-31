package ru.tatarchuk.darkweather.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.tatarchuk.darkweather.repo.DbModel;
import ru.tatarchuk.darkweather.repo.LocationModel;
import ru.tatarchuk.darkweather.repo.RestModel;
import ru.tatarchuk.darkweather.service.LocationService;
import ru.tatarchuk.darkweather.ui.daily.DailyActivityViewModel;
import ru.tatarchuk.darkweather.ui.main.navigation.NavigationViewModel;
import ru.tatarchuk.darkweather.ui.main.root.MainViewModel;

@Singleton
@Component(modules = {RestModule.class, AppModule.class})
public interface AppComponent {

    // repo
    //void inject(LocationModel model);
    void inject(RestModel model);
    void inject(DbModel model);
    /*void inject(DataBaseModel model);
    void inject(IconManager manager);

    // service
    void inject(LocationService service);

    // viewModel
    void inject(RootViewModel model);
    void inject(DayViewModel model);
    void inject(DailyViewModel model);
    void inject(NavigationViewModel model); */
    void inject(MainViewModel model);
    void inject(NavigationViewModel model);
    void inject(DailyActivityViewModel model);
    void inject(LocationModel model);
    void inject(LocationService service);
}