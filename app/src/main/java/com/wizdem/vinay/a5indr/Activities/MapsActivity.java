package com.wizdem.vinay.a5indr.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wizdem.vinay.a5indr.R;
import com.wizdem.vinay.a5indr.Utils.Utils;
import com.wizdem.vinay.a5indr.models.SaveLocation;

import java.util.Iterator;

import static com.wizdem.vinay.a5indr.R.id.donate_with;
import static com.wizdem.vinay.a5indr.R.id.textView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private BroadcastReceiver broadcastReceiver;
    private double mLatitude ;
    private double mLongitude;

    @Override
    protected void onPostResume() {
        super.onPostResume();
/*        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                   mLatitude = (double) intent.getExtras().get("latitude");
                    mLongitude = (double) intent.getExtras().get("longitude");
                    //  textView.setText(mCoordinates);
                }
            };

        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Iterator e=Utils.saveLocations.iterator();
        /*Iterator etr_lat=Utils.lat.iterator();
        Iterator etr_lon=Utils.lon.iterator();*/
        while (e.hasNext()){
            SaveLocation saveL = (SaveLocation)e.next();
            /*Double d = (Double)etr_lat.next();
            Double c=(Double)etr_lon.next();*/
            mMap.addMarker(new MarkerOptions().position(new LatLng(saveL.latitude,saveL.logitude)).title(saveL.message));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        // Add a marker in Sydney and move the camera

    }
}
