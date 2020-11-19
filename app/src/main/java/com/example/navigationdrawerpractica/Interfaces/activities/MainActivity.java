package com.example.navigationdrawerpractica.Interfaces.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navigationdrawerpractica.Interfaces.common.Constants;
import com.example.navigationdrawerpractica.Interfaces.domain.JsonResponsive;
import com.example.navigationdrawerpractica.Interfaces.domain.PoolList;
import com.example.navigationdrawerpractica.Interfaces.iface.PoolApi;
import com.example.navigationdrawerpractica.Interfaces.services.GpsService;
import com.example.navigationdrawerpractica.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.navigationdrawerpractica.Interfaces.common.Constants.INTENT_LOCALIZATION_ACTION;
import static com.example.navigationdrawerpractica.Interfaces.common.Constants.LONGITUDE;
import static com.example.navigationdrawerpractica.Interfaces.common.Constants.LATITUDE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final String TAG = getClass().getName();
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String TITLE = "Te encuentras aqui";
    public static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String DESCRIPTION = "Tu posicion actual";

    private List<PoolList> mPoolList;
    Double latitude = 0.0;
    Double longitude = 0.0;
    String title;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startService();
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(INTENT_LOCALIZATION_ACTION));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId() == R.id.home) {
            Log.d(TAG, "Value of latitude: ".concat(String.valueOf(latitude)));
            Intent locationIntent = new Intent(MainActivity.this, MapActivity.class);
            locationIntent.putExtra(TITLE_KEY, TITLE);
            locationIntent.putExtra(DESCRIPTION_KEY, DESCRIPTION);
            locationIntent.putExtra(LATITUDE, latitude);
            locationIntent.putExtra(LONGITUDE, longitude);
            startActivity(locationIntent);
        }
        if (menuItem.getItemId() == R.id.personas) {
            Toast.makeText(this, "HOLAAAA", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, PokemonActivity.class);
            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.home2) {
            Toast.makeText(this, "SOY UN TOAST!!!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = intent.getDoubleExtra(LATITUDE, 0);
            longitude = intent.getDoubleExtra(LONGITUDE, 0);
        }
    };

    //iniciamos el servicio, solo si se le ha dado permiso
    public void startService() {
        Intent mServiceIntent = new Intent(getApplicationContext(), GpsService.class);
        startService(mServiceIntent);
    }

    //nos dice el resultado del permiso, dependera de si acepta o no darnos permisos el mensaje que le salga
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), R.string.gps_granted, Toast.LENGTH_SHORT).show();
                startService();
            } else {
                Toast.makeText(getApplicationContext(), R.string.gps_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

  /*  public void getPools() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            PoolApi poolApi =retrofit.create(PoolApi.class);

            poolApi.getListPool(title, latitude, longitude).enqueue(new Callback<JsonResponsive>() {
                @Override
                public void onResponse(Call<JsonResponsive> call, Response<JsonResponsive> response) {
                    if (response != null && response.body() != null){
                    mPoolList = response.body().results;
                    }
                }

                @Override
                public void onFailure(Call<JsonResponsive> call, Throwable t) {

                }
            });
    }*/
}
