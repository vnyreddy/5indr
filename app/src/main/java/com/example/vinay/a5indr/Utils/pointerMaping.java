package com.example.vinay.a5indr.Utils;

import android.content.Context;
import android.location.Location;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.vinay.a5indr.R.color.blue_green;
import static com.example.vinay.a5indr.R.color.location_pointer;
import static com.example.vinay.a5indr.R.color.pointer_color;
import static com.example.vinay.a5indr.R.color.secondary_text_default_material_dark;

/**
 * Created by vinay_1 on 10/7/2016.
 */
public class pointerMaping {
    public double latitude=0;//Latitude
    public double longitude=0; //longitude
    private GoogleMap googleMap;
    private LatLng previous_loc;
    private LatLng current_loc;


    public pointerMaping(Location location) {

        latitude =location.getLatitude();
        longitude=location.getLongitude();
        current_loc = new LatLng(latitude, longitude);
    }
    public LatLng getCurrent_loc(){
        return current_loc;
    }
}
