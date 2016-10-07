package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

/**
 * Created by User on 10/4/2016.
 */

public class RestaurantOwnerPage extends AppCompatActivity {
    Button buttonLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        buttonLogOut = (Button)findViewById(R.id.buttonLogOutWelcome);

    }

    public void onClick(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(RestaurantOwnerPage.this,Login.class);
        startActivity(intent);
    }
}
