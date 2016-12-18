package com.example.vinay.a5indr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.vinay.a5indr.R;

/**
 * Created by vinay on 6/26/2016.
 */
public class SplashActivity extends AppCompatActivity {
    final int SPLASH_DISPLAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activitity_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent mainintent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainintent);
                SplashActivity.this.finish();*/
                //** below is the same code but reduced number of lines
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
