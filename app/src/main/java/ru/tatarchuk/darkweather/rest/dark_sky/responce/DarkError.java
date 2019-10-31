
package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import com.google.gson.annotations.SerializedName;

public class DarkError {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("error")
    private String mError;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

}
