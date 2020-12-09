package com.example.EjercicioMoviles1TRIM.Interfaces.Models;

import java.io.Serializable;

public class LocationModel implements Serializable {

    Double latitude;
    Double longitude;


    @Override
    public String toString() {
        return "LocationModel{" +
                "latitude=" + latitude +
                ", longlitude=" + longitude +
                '}';
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
