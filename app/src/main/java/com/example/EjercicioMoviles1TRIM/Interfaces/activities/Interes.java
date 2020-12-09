package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.EjercicioMoviles1TRIM.R;

import java.util.ArrayList;

public class Interes extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> webs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interes);

        listView = findViewById(R.id.listViewid);
        webs = new ArrayList<>();
        webs.add("Deportes comunidad de madrid");
        webs.add("Inscripcion actividades deportivas");
        webs.add("Piscinas Madrid");
        webs.add("Marca");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, webs);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent marca = new Intent(Interes.this, webActivity.class);
                    marca.putExtra("marca", 1);
                    startActivity(marca);
                }else if (position == 1){
                    Intent as = new Intent(Interes.this, webActivity.class);
                    as.putExtra("marca", 2);
                    startActivity(as);
                }else if (position == 2){
                    Intent sport = new Intent(Interes.this, webActivity.class);
                    sport.putExtra("marca", 3);
                    startActivity(sport);
                }else if (position == 3){
                    Intent mundo = new Intent(Interes.this, webActivity.class);
                    mundo.putExtra("marca", 4);
                    startActivity(mundo);

                }
            }
        });

    }
}