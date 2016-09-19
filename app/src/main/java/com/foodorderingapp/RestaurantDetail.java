package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 9/18/2016.
 */
public class RestaurantDetail extends AppCompatActivity {

    TextView textViewName;
    TextView textViewAddress;
    TextView textViewPhoneNumber;
    ImageView imageViewIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);

        Intent intent = getIntent();

        //Value is the following order
        ////name, address, phoneNumber, cuisine, priceRange, image URL
        ArrayList<String> restaurantDetails = intent.getStringArrayListExtra("ClickRestaurant");

        this.setTitle(restaurantDetails.get(0));

        textViewName = (TextView)findViewById(R.id.textViewRestaurantDetailName);
        textViewAddress = (TextView)findViewById(R.id.textViewRestaurantDetailAddress);
        textViewPhoneNumber = (TextView)findViewById(R.id.textViewRestaurantDetailPhoneNumber);
        imageViewIcon = (ImageView)findViewById(R.id.imageViewRestaurantDetail);

        textViewName.setText(restaurantDetails.get(0));
        textViewAddress.setText(restaurantDetails.get(1));
        textViewPhoneNumber.setText(restaurantDetails.get(2));
        
        //set logo of restaurant
        Picasso.with(RestaurantDetail.this)
                .load(restaurantDetails.get(5))
                .fit() // will explain later
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        Intent newIntent = new Intent(RestaurantDetail.this, test.class);
        GetJSON getJSON = new GetJSON(RestaurantDetail.this,"http://aaacars.co.nz/getMenu.php",test.class);
        RestaurantDetail.this.startActivity(newIntent);
    }
}
