package ru.tatarchuk.darkweather.rest.maps.responce;

import android.util.Log;

import androidx.core.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAddress extends ResponseBaseMap {

    private final String TAG = "Weather " + ResponseAddress.class.getSimpleName();

    @SerializedName("plus_code")
    @Expose
    private PlusCode plusCode;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void logAddress(){
        Pair address = getAddress();
        Log.i(TAG, address.first + " " + address.second);
    }

    public Pair<String, String> getAddress(){
        int priority = -1;
        String first = "";
        String second = "";
        for (Result result : results){
            if (priority < result.getSecondName().first){
                priority = result.getSecondName().first;
                second = result.getSecondName().second;
            }
            if (first.length() == 0){
                for (AddressComponent addressComponent : result.getAddressComponents()){
                    for (String string : addressComponent.getTypes()){
                        if (string.equals("locality")){
                            first = addressComponent.getLongName();
                        }
                    }
                }
            }

        }
        return new Pair<>(first, second);
    }

    public String getPlaceId(){
        if (results.size() > 0) {
            return results.get(0).getPlaceId();
        } else {
            return null;
        }
    }
}
