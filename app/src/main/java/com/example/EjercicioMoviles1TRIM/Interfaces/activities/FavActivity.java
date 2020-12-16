package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.EjercicioMoviles1TRIM.Interfaces.common.FavAdapter;
import com.example.EjercicioMoviles1TRIM.Interfaces.domain.Lugar;
import com.example.EjercicioMoviles1TRIM.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavActivity extends AppCompatActivity {
    private List<Lugar> mLugarList;
    private FavAdapter mFavAdapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        mListView = findViewById(R.id.listViewFav);
        mLugarList = getFavorites();

        mFavAdapter= new FavAdapter(FavActivity.this, mLugarList);
        mListView.setAdapter(mFavAdapter);
        mFavAdapter.notifyDataSetChanged();
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
}