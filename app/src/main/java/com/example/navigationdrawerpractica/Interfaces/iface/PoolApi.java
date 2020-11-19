package com.example.navigationdrawerpractica.Interfaces.iface;

import com.example.navigationdrawerpractica.Interfaces.common.Constants;
import com.example.navigationdrawerpractica.Interfaces.domain.JsonResponsive;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PoolApi {
    @Headers({"Accept: application/json , Content-Type: application/json"})
    @GET(Constants.END_POINT_POOL)
    Call<JsonResponsive> getListPool(@Query("title") String title, @Query("latitude") Double latitude, @Query("longitude") Double longitude);
}
