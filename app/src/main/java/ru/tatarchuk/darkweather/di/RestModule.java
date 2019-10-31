package ru.tatarchuk.darkweather.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.tatarchuk.darkweather.rest.dark_sky.DarkSkyApi;
import ru.tatarchuk.darkweather.rest.dark_sky.DarkSkyClient;
import ru.tatarchuk.darkweather.rest.maps.GoogleMapsApi;
import ru.tatarchuk.darkweather.rest.maps.MapsRestClient;

@Module
class RestModule {

    @Provides
    @Singleton
    MapsRestClient provideLocationClient(){
        return new MapsRestClient();
    }

    @Provides
    @Singleton
    GoogleMapsApi provideMapsApi(MapsRestClient client) {
        return client.createService(GoogleMapsApi.class);
    }

    @Provides
    @Singleton
    DarkSkyClient provideDarkSkyClient() {
        return new DarkSkyClient();
    }

    @Provides
    @Singleton
    DarkSkyApi provideDarkSkyApi(DarkSkyClient client) {
        return client.createService(DarkSkyApi.class);
    }
}
