package ru.tatarchuk.darkweather.db.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import ru.tatarchuk.darkweather.rest.maps.responce.Prediction;
import ru.tatarchuk.darkweather.rest.maps.responce.ResponseDetails;

@Entity
public class DataLocation {

    @PrimaryKey
    @NotNull
    private String mId;

    private String mFirstName;

    private String mSecondName;

    private Double mLat;

    private Double mLon;

    @Ignore
    public DataLocation(@NotNull String id) {
        mId = id;
    }

    public DataLocation(Prediction prediction, ResponseDetails details) {
        mId = prediction.getPlaceId();
        mFirstName = prediction.getStructuredFormatting().getMainText();
        mSecondName = prediction.getStructuredFormatting().getSecondaryText();
        mLat = details.getResult().getGeometry().getLocation().getLat();
        mLon = details.getResult().getGeometry().getLocation().getLng();
    }

    public DataLocation(@NotNull String id, String firstName, String secondName, Double lat, Double lon) {
        mId = id;
        mFirstName = firstName;
        mSecondName = secondName;
        mLat = lat;
        mLon = lon;
    }

    @NotNull
    public String getId() {
        return mId;
    }

    public void setId(@NotNull String id) {
        mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getSecondName() {
        return mSecondName;
    }

    public void setSecondName(String secondName) {
        mSecondName = secondName;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }
}
