package com.example.EjercicioMoviles1TRIM.Interfaces.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    @SerializedName("@graph")
    @Expose
    public List<Lugar> graph = null;




}
