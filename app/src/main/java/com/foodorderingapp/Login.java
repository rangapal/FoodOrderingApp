package com.foodorderingapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 9/21/2016.
 */

import android.os.Bundle;

import com.facebook.FacebookSdk;

/*
    This class is for Facebook Login
 */
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_activity);
    }
}
