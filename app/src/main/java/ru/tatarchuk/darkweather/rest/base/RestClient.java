package ru.tatarchuk.darkweather.rest.base;

import android.util.Log;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class RestClient {

    private final String TAG = "Weather " + RestClient.class.getSimpleName();

    private Retrofit mRetrofit;

    public RestClient() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .baseUrl(getBaseUrl())
                .build();
    }

    private OkHttpClient createOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    /** В этом методе производные классы добавляют кастомные десиреализаторы в qson*/
    public abstract Gson createGson();

    public <S> S createService(Class<S> serviceClass){
        return mRetrofit.create(serviceClass);
    }

    protected abstract String getBaseUrl();
}
