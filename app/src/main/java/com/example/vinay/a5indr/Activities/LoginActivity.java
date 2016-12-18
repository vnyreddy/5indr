package com.example.vinay.a5indr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vinay.a5indr.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;

/**
 * Created by vinay on 6/26/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, FacebookCallback<LoginResult> {
    private CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_activity_layout);
        Button login_buttion1 = (Button) findViewById(R.id.login_button_sample);
        login_buttion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });



    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onCancel() {
        Toast.makeText(LoginActivity.this, "Login Canceled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(FacebookException e) {
        Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

    }
}
