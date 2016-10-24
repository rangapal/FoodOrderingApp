package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This class is for displaying restaurant information that user selected
 */
public class RestaurantDetail extends NavigationDrawerUser {

    TextView textViewName;
    TextView textViewAddress;
    TextView textViewPhoneNumber;
    TextView textViewPriceRange;
    ImageView imageViewIcon;
    private String restaurantName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_detail);

        navigation_drawer();//set the toolbar and navigation drawer

        Intent intent = getIntent();
        //Value is the following order
        //name, address, phoneNumber, cuisine, priceRange, image URL, ID
        ArrayList<String> restaurantDetails = intent.getStringArrayListExtra("ClickRestaurant");

        restaurantName = restaurantDetails.get(0);
        this.setTitle(restaurantName); // set restaurant name as title of the screen

        textViewName = (TextView)findViewById(R.id.textViewRestaurantDetailName);
        textViewAddress = (TextView)findViewById(R.id.textViewRestaurantDetailAddress);
        textViewPhoneNumber = (TextView)findViewById(R.id.textViewRestaurantDetailPhoneNumber);
        imageViewIcon = (ImageView)findViewById(R.id.imageViewRestaurantDetail);
        textViewPriceRange = (TextView) findViewById(R.id.textViewRestaurantDetailPriceRange);

        textViewName.setText(restaurantName);//set the restaurant details and display on screen
        textViewAddress.setText(restaurantDetails.get(1));
        textViewPhoneNumber.setText(restaurantDetails.get(2));
        textViewPriceRange.setText(restaurantDetails.get(4));
        
        //set logo of restaurant
        Picasso.with(RestaurantDetail.this)
                .load(restaurantDetails.get(5)) // 5 is the index for image URL
                .fit()
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                RestaurantDetail.this,"http://aaacars.co.nz/getMenu.php",initiateMenuDisplay(),restaurantName);
        connectDB.execute();
    }

    //this method is for connect to database and get JSONstring of restaurant information
    private AsyncResponse initiateMenuDisplay(){
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(RestaurantDetail.this, MenuDisplay.class);
                intent.putExtra("JSONStringMenu", s);
                intent.putExtra("restaurantID",restaurantName);
                startActivity(intent);
            }
        };
        return asyncResponse;
    }
}
