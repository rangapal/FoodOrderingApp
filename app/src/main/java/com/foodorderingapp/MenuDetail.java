package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuDetail extends AppCompatActivity {

    TextView textViewName;
    TextView textViewAddress;
    TextView textViewPhoneNumber;
    ImageView imageViewIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail);

        Intent intent = getIntent();

        //Value is the following order
        ////restaurantName, menuName, menuPrice, menuDescription, menuImage
        ArrayList<String> restaurantDetails = intent.getStringArrayListExtra("ClickRestaurant");

        this.setTitle(restaurantDetails.get(0));

        textViewName = (TextView)findViewById(R.id.textViewRestaurantDetailName);
       imageViewIcon = (ImageView)findViewById(R.id.imageViewRestaurantDetail);

        textViewName.setText(restaurantDetails.get(0));
        textViewAddress.setText(restaurantDetails.get(1));
        textViewPhoneNumber.setText(restaurantDetails.get(2));
        
        //set logo of restaurant
        Picasso.with(MenuDetail.this)
                .load(restaurantDetails.get(5))
                .fit() // will explain later
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        Intent newIntent = new Intent(MenuDetail.this, test.class);
        //GetJSON getJSON = new GetJSON(MenuDetail.this,"http://aaacars.co.nz/getMenu.php",test.class);
        MenuDetail.this.startActivity(newIntent);
    }
}
