/*
package com.example.vinay.a5indr;
//This class is not in use
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.*;

*/
/**
 * Created by vinay on 7/31/2016.
 * but not used any where
 *//*

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
    private final Context mContext;
    private GoogleMap googleMap;
    private GoogleApiClient client;

    public GoogleMapFragment(Context context) {
        this.mContext = context;
        getGoogleMap();
    }
    public void getGoogleMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = new GoogleApiClient.Builder(mContext).addApi(AppIndex.API).build();
     //   return mapFragment;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //  googleMap.setMyLocationEnabled(true);
        FindLocation findLocation=new FindLocation(mContext.getApplicationContext());
        com.google.android.gms.maps.model.LatLng current_loc = new com.google.android.gms.maps.model.LatLng(findLocation.latitude,findLocation.longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current_loc, 13));
        googleMap.addMarker(new MarkerOptions()
                .title("You")
                .snippet("Current location.")
                .position(current_loc));
    }

}
*/
