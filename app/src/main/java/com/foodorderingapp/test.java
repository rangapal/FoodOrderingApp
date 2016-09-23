package com.foodorderingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class test extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        button = (Button)findViewById(R.id.buttonGet);

    }

    public void onClick(View view) {
//        HomeFragment homeFragment = new HomeFragment();
//        Intent intent = new Intent(test.this, homeFragment.getClass());
//        test.this.startActivity(intent);

        GetJSON getJSON = new GetJSON(test.this,"http://aaacars.co.nz/getRestaurant.php",RestaurantDisplay.class,null);

        //String s = getJSON.getJsonString();
    }
}
