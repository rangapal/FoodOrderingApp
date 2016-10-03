package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    TextView textViewPriceRange;
    ImageView imageViewIcon;
    private String restaurantName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);

        Intent intent = getIntent();

        //Value is the following order
        //name, address, phoneNumber, cuisine, priceRange, image URL, ID
        ArrayList<String> restaurantDetails = intent.getStringArrayListExtra("ClickRestaurant");

        restaurantName = restaurantDetails.get(0);
        this.setTitle(restaurantName);

        textViewName = (TextView)findViewById(R.id.textViewRestaurantDetailName);
        textViewAddress = (TextView)findViewById(R.id.textViewRestaurantDetailAddress);
        textViewPhoneNumber = (TextView)findViewById(R.id.textViewRestaurantDetailPhoneNumber);
        imageViewIcon = (ImageView)findViewById(R.id.imageViewRestaurantDetail);
        textViewPriceRange = (TextView) findViewById(R.id.textViewRestaurantDetailPriceRange);

        //set the restaurant details on screen
        textViewName.setText(restaurantName);
        textViewAddress.setText(restaurantDetails.get(1));
        textViewPhoneNumber.setText(restaurantDetails.get(2));
        textViewPriceRange.setText(restaurantDetails.get(4));
        
        //set logo of restaurant
        Picasso.with(RestaurantDetail.this)
                .load(restaurantDetails.get(5))
                .fit() // will explain later
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        //this string is pass to menu activity so we know which restaurant is select
        //then we can populate the menu of that restaurant

        ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                RestaurantDetail.this,"http://aaacars.co.nz/getMenu.php",initiateMenuDisplay(),restaurantName);
        connectDB.execute();

        //GetJSON getJSON = new GetJSON(RestaurantDetail.this,"http://aaacars.co.nz/getMenu.php",MenuDisplay.class,restaurantName);
    }

    private AsyncResponse initiateMenuDisplay(){
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(RestaurantDetail.this, MenuDisplay.class);
                intent.putExtra("JSONString", s);
                intent.putExtra("restaurantID",restaurantName);
                startActivity(intent);
            }
        };

        return asyncResponse;
    }
}
