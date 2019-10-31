package ru.tatarchuk.darkweather.repo;

import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ru.tatarchuk.darkweather.WeatherApp;

public class LocationModel {

    public static final String TAG = "Weather " + LocationModel.class.getSimpleName();

    private final static long UPDATE_INTERVAL = TimeUnit.MINUTES.toMillis(30);
    private final static long FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 2;
    private final static long SMALLEST_DISPLACEMENT = 3000;

    @Inject
    FusedLocationProviderClient mLocationClient;

    private LocationRequest mRequest;

    public LocationModel() {
        WeatherApp.getAppComponent().inject(this);
    }

    public void requestUpdate(LocationCallback callback) {
        try {
            mLocationClient.requestLocationUpdates(createLocationRequest(), callback, null);
        } catch (SecurityException unlikely) {
            Log.e(TAG, "Lost location permission. Could not request updates. " + unlikely);
        }
    }

    public void removeUpdate(LocationCallback callback) {
        if (callback != null) {
            mLocationClient.removeLocationUpdates(callback);
        }
    }

    private LocationRequest createLocationRequest() {
        if (mRequest == null) {
            mRequest = new LocationRequest()
                    .setInterval(UPDATE_INTERVAL)
                    .setFastestInterval(FASTEST_UPDATE_INTERVAL)
                    .setSmallestDisplacement(SMALLEST_DISPLACEMENT)
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        }
        return mRequest;
    }
}