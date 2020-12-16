package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants;
import com.example.EjercicioMoviles1TRIM.Interfaces.common.PiscinasAdapter;
import com.example.EjercicioMoviles1TRIM.Interfaces.common.PoolAdapter;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.JsonResponse;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.Lugar;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.PoolList;
import com.example.EjercicioMoviles1TRIM.Interfaces.iface.ApiMadrid;
import com.example.EjercicioMoviles1TRIM.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Lugar> mLugarList;
    private PiscinasAdapter mPiscinaAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        //Intent getData = getIntent();
        //Toast.makeText(getApplicationContext(), String.valueOf(getData.getStringExtra("time")), Toast.LENGTH_SHORT).show();

        getAllPiscinas();
        mListView = findViewById(R.id.listViewPiscina);
        mListView.setOnCreateContextMenuListener((contextMenu, view, contextMenuInfo) -> {
            contextMenu.add(0, 1 , 0,"Add to favorites");
            contextMenu.add(0, 2 , 0,"Go to");
        });
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String valor = String.valueOf(mPiscinaAdapter.getItemId(i));
                Lugar lugar = (Lugar) mPiscinaAdapter.getItem(i);
                addFavorite(lugar);
                //String titulo = lugar.getTitle();
                //Toast.makeText(SwimActivity.this, titulo , Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == 1){
            Lugar lugar = (Lugar) mPiscinaAdapter.getItem(info.position);
            addFavorite(lugar);
        }else if(item.getItemId() == 2){
            Toast.makeText(this, "Boton2", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private Boolean checkExistFavorite(ArrayList<Lugar> favorites, Lugar lugar){
        for (Lugar l: favorites){
            if (l.getId() == lugar.getId()){
                return false;
            }
        }
        return true;
    }

    private void addFavorite(Lugar lugar){
        ArrayList<Lugar> favorites = getFavorites();

        if(favorites == null){
            favorites = new ArrayList<>();
        }else{
            if(checkExistFavorite(favorites, lugar)){
                favorites.add(lugar);
            }
        }
        saveFavorites(favorites);
    }

    private ArrayList<Lugar> getFavorites() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        List<Lugar> favorites;

        if (preferences.contains("favorites")) {
            String jsonFavorites = preferences.getString("favorites", null);
            Gson gson = new Gson();
            Lugar[] favoriteItems = gson.fromJson(jsonFavorites, Lugar[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else{
            return null;
        }
        return (ArrayList<Lugar>) favorites;
    }

    private void saveFavorites(ArrayList<Lugar> favorites){
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(favorites);
        editor.putString("favorites", json);
        editor.commit();

        Toast.makeText(PoolActivity.this, "Añadido a favoritos" , Toast.LENGTH_SHORT).show();
    }

    private void removeFavorite(Lugar lugar) {
        ArrayList<Lugar> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(lugar);
            saveFavorites(favorites);
        }
    }



    private void getAllPiscinas(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiMadrid apiMadrid = retrofit.create(ApiMadrid.class);
        apiMadrid.getPisicnasList().enqueue(new Callback<JsonResponse>() {
            //Hace una peticion y se van generando los datos
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                mLugarList = response.body().graph;

                mPiscinaAdapter= new PiscinasAdapter(PoolActivity.this, mLugarList);
                mListView.setAdapter(mPiscinaAdapter);
                mPiscinaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}