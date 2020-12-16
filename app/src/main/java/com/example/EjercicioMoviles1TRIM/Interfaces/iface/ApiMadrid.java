package com.example.EjercicioMoviles1TRIM.Interfaces.iface;

import com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiMadrid {
    //Indicamos como solicitamos la peticion y como la recibimos (en este caso Json)
    @Headers({"Accept: application/json , Content-Type: application/json"})
    //inciamos el end point y el tipo de peticion y abajo el Json donde lo guardaremos y en caso de tener parametros los indicamos
    @GET(Constants.PISCINAS_END_POINT)
    Call<JsonResponse> getListPool(@Query("distancia") int distance, @Query("latitud") Double latitude, @Query("longitud") Double longitude);


    @Headers({"Accept: application/json , Content-Type: application/json"})
    @GET(Constants.PISCINAS_END_POINT)
    Call<JsonResponse> getPisicnasList();

    @Headers({"Accept: application/json , Content-Type: application/json"})
    @GET(Constants.POLI_END_POINT)
    Call<JsonResponse> getPoliList();
}
