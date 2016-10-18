package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/**
 * Created by User on 9/18/2016.
 */
public class RestaurantDisplay extends NavigationDrawerUser {
    ListView listViewRestaurant;
    private final String JSONARRAY = "Restaurant";
    TreeMap<String, ArrayList<String>> restaurantTreeMap;
    Object[] restaurantValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list_view);

        //set the toolbar and navigation drawer
        navigation_drawer();

        listViewRestaurant = (ListView) findViewById(R.id.listViewRestaurant);

        //get the JSONString and convert it into treemap
        Intent intent = getIntent();
        String JSONString = intent.getStringExtra("JSONString");
        ConvertJSON convertJSON = new ConvertJSON(JSONString,JSONARRAY);

        //Value in TreeMap is the following order
        //name, address, phoneNumber, cuisine, priceRange, imageURL
        restaurantTreeMap = convertJSON.getTreeMap();

        //Convert treemap into object array to pass into adapter
        Collection<ArrayList<String>> collection = restaurantTreeMap.values();
        restaurantValues = collection.toArray();

        listViewRestaurant.setAdapter(new RestaurantDisplayAdapter(RestaurantDisplay.this, restaurantValues));

        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> restaurantDetailArray = (ArrayList<String>) restaurantValues[position];

                Intent restaurantDetail = new Intent(RestaurantDisplay.this,RestaurantDetail.class);
                restaurantDetail.putExtra("ClickRestaurant", restaurantDetailArray);
                RestaurantDisplay.this.startActivity(restaurantDetail);

            }
        });
    }
}
