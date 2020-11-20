package com.example.EjercicioMoviles1TRIM.Interfaces.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordenadas {
    @SerializedName("latitude")
    @Expose
    Double latitud;
    @SerializedName("longitude")
    @Expose
    Double longitud;

    public Coordenadas(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
