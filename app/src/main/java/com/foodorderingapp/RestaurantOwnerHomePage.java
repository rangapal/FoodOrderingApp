package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

/**
 * Home page for restaurant owner when they log in
 */

public class RestaurantOwnerHomePage extends AppCompatActivity {
    Button buttonLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_owner_homepage);
        buttonLogOut = (Button)findViewById(R.id.buttonLogOutWelcome);
    }

    public void onClick(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(RestaurantOwnerHomePage.this,Login.class);
        startActivity(intent);
    }
}
