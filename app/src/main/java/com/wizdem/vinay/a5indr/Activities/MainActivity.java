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


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private TextView textView;
    private Intent intent;
    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ImageView mGoogleUserImg;
    private FirebaseUser user;
    private TextView mGoogleUserName;
    private Button mSaveLocation;
    private static String mCoordinates;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDB_Reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runtime_permissions();
        mGoogleUserName = (TextView) findViewById(R.id.google_user_name);
        mGoogleUserImg = (ImageView)findViewById(R.id.google_user_img);
        textView = (TextView) findViewById(R.id.text);
        mLogOutBtn = (Button) findViewById(R.id.google_logout);
        mSaveLocation = (Button) findViewById(R.id.save_location);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDB_Reference = mFirebaseDatabase.getReference("message1");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser() != null){
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                mGoogleUserName.setText(name);
                String email = profile.getEmail();
                String photoUrl = String.valueOf(profile.getPhotoUrl());
                photoUrl= photoUrl.replace("/s96-c/","/s300-c/");
                Picasso.with(this).load(photoUrl).into(mGoogleUserImg);
            };
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
                saveLocation();
            }
        });
    }

    private void saveLocation() {
        mFirebaseDB_Reference.setValue(mCoordinates);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mCoordinates = (String) intent.getExtras().get("coordinates");
                    textView.setText(mCoordinates);
                }
            };

        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);

        }
//        stopService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else runtime_permissions();
        }
    }

    //*************Requesting permissions**********************************************************************************
    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Intent intent = new Intent(getApplicationContext(), FindLocation.class);
        startService(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
