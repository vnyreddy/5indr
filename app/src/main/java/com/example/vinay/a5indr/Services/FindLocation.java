package com.example.vinay.a5indr.Services;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.vinay.a5indr.Utils.pointerMaping;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.vinay.a5indr.R.color.pointer_color;

/*
 Created by vinay on 7/29/2016.
*/
public class FindLocation extends Service implements LocationListener {
    private Context mContext;
    private GoogleMap googleMap;
    protected LocationManager locationManager;
    Location location; //Locatoin
    boolean isGPSEnabled = false; // flag for network status
    boolean isNetworkEnabled = false; // flag for GPS status
    boolean canGetLocation = false;
    //minimum distance to change the update in meters
    private static final long MIN_DISTANCE_FOR_UPDATE_CHANGE = 1; //meters
    //minimum time between to change the update in seconds
    private static final long MIN_TIME_BW_UPDATE_CHANGE = 1000 * 2;//seconds milli
    //   private LatLng current_loc;
    private Marker marker;
    public LatLng cu_lo;
    public Activity activity;

    public FindLocation(Context context, GoogleMap googleMap, Activity activity) {
        this.mContext = context;
        this.googleMap = googleMap;
        this.activity = activity;
        getLocation();
        location_pointer_marker();
    }


    public Location getLocation() {
        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            //getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                //no network provider is enabled
                Toast.makeText(mContext, "Location Source Not Found!!", Toast.LENGTH_LONG).show();
            } else {
                this.canGetLocation = true;
                //first get location from GPS Provider
                //if GPS Enabled get lat/long using GPS Services

                }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {
            location_pointer_marker();
    }
    public void location_pointer_marker(){
        pointerMaping pointerMaping= new pointerMaping(location);
        cu_lo= pointerMaping.getCurrent_loc();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cu_lo, 17));
        googleMap.addCircle((new CircleOptions()).center(cu_lo).radius(4)
                .strokeColor(pointer_color)
                .fillColor(pointer_color));
        if (marker != null) {
            marker.remove();
        }

        marker = googleMap.addMarker(new MarkerOptions()
                .title("You")
                .snippet("Current location.")
                .position(cu_lo));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
