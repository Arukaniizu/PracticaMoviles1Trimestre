package com.example.navigationdrawerpractica.Interfaces.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.example.navigationdrawerpractica.Interfaces.common.Constants.INTENT_LOCALIZATION_ACTION;
import static com.example.navigationdrawerpractica.Interfaces.common.Constants.LONGITUDE;
import static com.example.navigationdrawerpractica.Interfaces.common.Constants.LATITUDE;

public class GpsService extends Service implements LocationListener {


    private LocationManager mLocationManager =null;
    Double longitude = 0.0;
    Double latitude = 0.0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        startLocation();

        return START_STICKY;
    }

    @SuppressLint("MissingPermission")
    private void startLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
      //  if (!mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            callGPSSettingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(callGPSSettingIntent);
        } else {
            //GPSPROVIDER para hacerlo desde el emulador
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 100, this);
            //NETWORK_PROVIDER para hacerlo desde el movil
           // mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 100, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Intent intent = new Intent(INTENT_LOCALIZATION_ACTION);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}

