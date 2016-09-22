package com.foodorderingapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 9/21/2016.
 */

import android.os.Bundle;

import com.facebook.FacebookSdk;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_activity);

        //1338482536164282 myid
        //1589787581323737 dom id
        //1265187020178738 ranga id


    }
}
