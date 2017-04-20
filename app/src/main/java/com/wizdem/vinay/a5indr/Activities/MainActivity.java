package com.wizdem.vinay.a5indr.Activities;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.wizdem.vinay.a5indr.R;
import com.wizdem.vinay.a5indr.Services.FindLocation;
import com.wizdem.vinay.a5indr.Utils.Utils;
import com.wizdem.vinay.a5indr.models.SaveLocation;
import com.wizdem.vinay.a5indr.models.Users;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private TextView textView;
    private EditText editText;
    private Intent intent;
    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ImageView mGoogleUserImg;
    private FirebaseUser user;
    private TextView mGoogleUserName;
    private Button mSaveLocation;
    private Button mViewMap;
    private Button mViewHistory;
    private static String mCoordinates;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDB_Reference;
   // private Utils uid;
    private String name;
    private String email;
    private SimpleDateFormat mDateStamp;
    private String cMessage;
    private String mStamp;
  //  private String mLatitude;
  //  private String mLongitude;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // runtime_permissions();
        mGoogleUserName = (TextView) findViewById(R.id.google_user_name);
        mGoogleUserImg = (ImageView)findViewById(R.id.google_user_img);
        textView = (TextView) findViewById(R.id.text);
        mLogOutBtn = (Button) findViewById(R.id.google_logout);
        editText = (EditText)findViewById(R.id.message_id);
        mViewMap = (Button)findViewById(R.id.map_view);
        mViewHistory = (Button) findViewById(R.id.view_history);

        mSaveLocation = (Button) findViewById(R.id.save_location);
        mDateStamp = new SimpleDateFormat("ddMMyyyyhhmmss");
        mStamp = mDateStamp.format(new Date());

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mFirebaseDB_Reference = mFirebaseDatabase.getReference();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        
        if(mAuth.getCurrentUser() != null){

                // Id of the provider (ex: google.com)
                String providerId = user.getProviderId();

                // UID specific to the provider
                Utils.uid = user.getUid();

                // Name, email address, and profile photo Url
               Utils.user_name = user.getDisplayName();
                mGoogleUserName.setText(Utils.user_name);
               email = user.getEmail();
                Utils.photoUrl = String.valueOf(user.getPhotoUrl());
                String photoUrl= Utils.photoUrl.replace("/s96-c/","/s300-c/");
                Picasso.with(this).load(photoUrl).into(mGoogleUserImg);
        }
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

            }
        });
        mSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double mLatitude = Utils.latitude;
                double mLongitude = Utils.longitude;
                textView.setText("Your location: "+mLatitude+" "+mLongitude);
                cMessage = editText.getText().toString().trim();
                writeNewUser(Utils.uid,Utils.user_name,email);
                saveLocation(cMessage,mLatitude,mLongitude,mStamp);
            }
        });

        mViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        mViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HistoryActivity.class));
            }
        });
    }



    private void saveLocation(String message, double longitude, double latitude, String time) {
    //    String stamp = mDateStamp.format(new Date());
        SaveLocation saveLocation = new SaveLocation(message,latitude,longitude,time);
        mFirebaseDB_Reference.child("Location").child(Utils.uid).child(mStamp).setValue(saveLocation);
       // mFirebaseDB_Reference.child("Entry").child(mStamp).setValue(saveLocation);



    }
    private void writeNewUser(String userID, String name, String email){
        Users user = new Users(name,email);
        mFirebaseDB_Reference.child("User").child(userID).setValue(user);
    }
/*
    @Override
    protected void onResume() {
        super.onResume();

*//*        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                   double mLatitude = (double) intent.getExtras().get("latitude");
                   double mLongitude = (double) intent.getExtras().get("longitude");
                    textView.setText(mLatitude+" "+mLongitude);
                }
            };

        }*//*
      //  registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
//        stopService(intent);
    /*    if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);

        }*/
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else runtime_permissions();
        }
    }

    /*//*************Requesting permissions**********************************************************************************
    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }*/

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Intent intent = new Intent(getApplicationContext(), FindLocation.class);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
