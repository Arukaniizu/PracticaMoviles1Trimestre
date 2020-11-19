package com.example.navigationdrawerpractica.Interfaces.domain;

public class PoolList {

    private String title;
    private Double latitude;
    private Double longitude;

    public PoolList() {

    }

    public PoolList(String title, Double latitude, Double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
