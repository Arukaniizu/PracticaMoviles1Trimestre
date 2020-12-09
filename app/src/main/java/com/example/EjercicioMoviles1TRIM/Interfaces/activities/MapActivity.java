package com.example.EjercicioMoviles1TRIM.Interfaces.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EjercicioMoviles1TRIM.Interfaces.Models.LocationModel;
import com.example.EjercicioMoviles1TRIM.R;
import com.google.gson.Gson;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.LONGITUDE;
import static com.example.EjercicioMoviles1TRIM.Interfaces.common.Constants.LATITUDE;
import static com.example.EjercicioMoviles1TRIM.Interfaces.activities.MainActivity.DESCRIPTION_KEY;
import static com.example.EjercicioMoviles1TRIM.Interfaces.activities.MainActivity.TITLE_KEY;

public class MapActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private MapView mMapView;
    private MapController mMapController;
    GeoPoint geoPointMyPosition;
    private ArrayList<OverlayItem> mOverlayItems = new ArrayList<>();
    LocationModel locationModel = new LocationModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);




        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        Intent getDataIntent = getIntent();
        geoPointMyPosition = new GeoPoint(getDataIntent.getDoubleExtra(LATITUDE, 0), getDataIntent.getDoubleExtra(LONGITUDE, 0));
        generateOpenStreetMapViewAndMapController();
        locationModel.setLatitude(getDataIntent.getDoubleExtra(LATITUDE, 0));
        locationModel.setLongitude(getDataIntent.getDoubleExtra(LONGITUDE, 0));
        boolean add = mOverlayItems.add(new OverlayItem(getDataIntent.getStringExtra(TITLE_KEY), getDataIntent.getStringExtra(DESCRIPTION_KEY), geoPointMyPosition));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(mOverlayItems, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        }, getApplicationContext());

        mOverlay.setFocusItemsOnTap(true);
        mMapView.getOverlays().add(mOverlay);


        Button botonFavorito = findViewById(R.id.botonFavorito);

        botonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapActivity.this, locationModel +"", Toast.LENGTH_SHORT).show();
                SharedPreferences prefs =  getSharedPreferences("Ubicacion",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Gson gson = new Gson();
                String locationString = gson.toJson(locationModel);
                editor.putString("myLocation", locationString);
                editor.commit();
                Toast.makeText(MapActivity.this, "Guardada la ubicación actual", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void generateOpenStreetMapViewAndMapController() {
        mMapView = (MapView) findViewById(R.id.openmapview);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(18);
        mMapController.setCenter(geoPointMyPosition);
    }
}