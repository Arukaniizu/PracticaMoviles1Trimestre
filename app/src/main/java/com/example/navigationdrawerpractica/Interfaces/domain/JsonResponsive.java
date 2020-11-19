package com.example.navigationdrawerpractica.Interfaces.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponsive {



    /*@SerializedName("@graph")
    @Expose
    public List<PoolList> results = null;*/

    @SerializedName("results")
    @Expose
    public List<PokemonList> results = null;


}
