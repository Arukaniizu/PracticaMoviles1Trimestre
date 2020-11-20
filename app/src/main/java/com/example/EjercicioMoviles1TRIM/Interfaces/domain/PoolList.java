package com.example.EjercicioMoviles1TRIM.Interfaces.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoolList {
    //Solicitamos el titele y el Clase coordenadas con la latitud y la longitud
    //@serializableName es el "titulo" que tiene en el Json y el @Expose es para que no lo mande null.
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location")
    @Expose
    private Coordenadas coordenadas;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }
}
