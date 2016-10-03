package com.foodorderingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 9/23/2016.
 */
public class ButtonGoToRestaurantSelection extends AppCompatActivity{
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //button change
        button = (Button)findViewById(R.id.buttonOrder);
    }

    public void onClick(View view) {
//        HomeFragment homeFragment = new HomeFragment();
//        Intent intent = new Intent(testClass.this, homeFragment.getClass());
//        testClass.this.startActivity(intent);

//        GetJSON getJSON = new GetJSON(ButtonGoToRestaurantSelection.this,"http://aaacars.co.nz/getRestaurant.php",RestaurantDisplay.class,null);


        //String s = getJSON.getJsonString();
    }

}
