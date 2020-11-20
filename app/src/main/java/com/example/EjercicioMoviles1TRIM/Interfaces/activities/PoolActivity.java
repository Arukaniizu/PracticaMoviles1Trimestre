package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants;
import com.example.EjercicioMoviles1TRIM.Interfaces.common.PoolAdapter;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.JsonResponsive;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.PoolList;
import com.example.EjercicioMoviles1TRIM.Interfaces.iface.PoolApi;
import com.example.EjercicioMoviles1TRIM.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoolActivity extends AppCompatActivity {
    private List<PoolList> mPoolList;
    private ArrayList<PoolList> rellenarPoolList;
    ListView piscinasLv;
    PoolList poolList;
    PoolAdapter poolAdapter;
    Double latitude = 40.4173208;
    Double longitude = -3.7063557;
    int  distance = 8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        piscinasLv = findViewById(R.id.lvPiscinas);
        getPools();
        piscinasLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //i es la posicion en la que gas clickado y el long es la id de la posicion
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PoolActivity.this, "opcion " + i, Toast.LENGTH_LONG).show();
                //   IN MEMORIAN
                //Log.d("Posicion", "Has seleccionado la opcion ");
            }
        });
    }

    public void getPools() {
        rellenarPoolList = new ArrayList<>();
        //llamamos al retrofit llamamos a la api y lo convertimos
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PoolApi poolApi =retrofit.create(PoolApi.class);
        //llamamos al metodo de la api
        poolApi.getListPool(distance, latitude, longitude).enqueue(new Callback<JsonResponsive>() {
            @Override
            //Aqui solicitamos los datos del getlistpool y cogemos los datos.
            public void onResponse(Call<JsonResponsive> call, Response<JsonResponsive> response) {
                if (response != null && response.body() != null){
                    mPoolList = response.body().results;
                    for (PoolList x:mPoolList) {
                        poolList = new PoolList();
                        poolList.setTitle(x.getTitle());
                        poolList.setCoordenadas(x.getCoordenadas());
                        rellenarPoolList.add(poolList);
                    }
                }
                poolAdapter = new PoolAdapter(PoolActivity.this, rellenarPoolList, R.layout.activity_main );
                //metemos el poolAdapter en nuestro LV
                piscinasLv.setAdapter(poolAdapter);
                //Este nos permite recargar cuando hay cambios
                poolAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<JsonResponsive> call, Throwable t) {

            }
        });
    }
}