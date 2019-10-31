package ru.tatarchuk.darkweather.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import org.threeten.bp.Instant;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.repo.DbModel;
import ru.tatarchuk.darkweather.repo.LocationModel;
import ru.tatarchuk.darkweather.repo.RestModel;
import ru.tatarchuk.darkweather.utils.SystemUtils;

public class LocationService extends Service {

    public static final String TAG = "Weather " + LocationService.class.getSimpleName();

    private static final int NOTIFICATION_ID = 3011;
    private static final String CHANNEL_ID = "weather_channel_3011";

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private RemoteViews mRemoteViews;
    private PendingIntent mPendingIntent;

    private LocationCallback mLocationCallback;

    private Disposable mNetDisposable;
    private Disposable mDbDisposable;

    private BroadcastReceiver mScreenOnReceiver;
    private BroadcastReceiver mScreenOfReceiver;
    private BroadcastReceiver mNetReceiver;

    @Inject
    DbModel mDataBase;

    @Inject
    LocationModel mLocationModel;

    @Inject
    RestModel mRestModel;

    private final IBinder mBinder = new LocationBinder();

    public class LocationBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WeatherApp.getAppComponent().inject(this);
        mLocationModel.requestUpdate(getLocationCallBack());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationModel.removeUpdate(mLocationCallback);
        mLocationCallback = null;
    }

    private LocationCallback getLocationCallBack() {
        if (mLocationCallback == null) {
            mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLocations().get(0);
                    Log.i(TAG, "new coord: " + location.getLatitude() + " " + location.getLongitude());
                    if (mNetDisposable != null) mNetDisposable.dispose();
                    mNetDisposable = mRestModel.loadCurrentLocation(location.getLatitude(), location.getLongitude())
                            .subscribe(t -> mDataBase.addLocation(t), e -> {
                                Toast.makeText(LocationService.this, "Error update", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "location error update ", e);
                            });
                }
            };
        }
        return mLocationCallback;
    }

    private void registerScreenReceivers() {
        if (mScreenOnReceiver == null) {
            mScreenOnReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mLocationModel.requestUpdate(getLocationCallBack());
                    //getCurrentWeather();
                    Log.i(TAG, "Screen On");
                }
            };
        }

        if (mScreenOfReceiver == null) {
            mScreenOfReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mLocationModel.removeUpdate(mLocationCallback);
                    mLocationCallback = null;
                    Log.i(TAG, "Screen Of");
                }
            };
        }
        registerReceiver(mScreenOnReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mScreenOfReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    private void registerNetBroadcast() {
        Log.i(TAG, "register broadcast");
        try {
            if (mNetReceiver == null) {
                mNetReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.i(TAG, "broadcast onReceive");
                        if (SystemUtils.isConnected()) {
                            mLocationModel.requestUpdate(getLocationCallBack());
                            unregisterReceiver(mNetReceiver);
                        }
                    }
                };
                registerReceiver(mNetReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            }
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "receiver is already registered", e);
        }
    }

    private void unregisterNetBroadcast() {
        try {
            if (mNetReceiver != null) {
                unregisterReceiver(mNetReceiver);
                mNetReceiver = null;
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "receiver is not registered ", e);
        }
    }

    private void unregisterScreenReceiver() {
        if (mScreenOnReceiver != null) {
            unregisterReceiver(mScreenOnReceiver);
            mScreenOnReceiver = null;
        }

        if (mScreenOfReceiver != null) {
            unregisterReceiver(mScreenOfReceiver);
            mScreenOfReceiver = null;
        }
    }
}
