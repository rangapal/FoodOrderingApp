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
 * This class is for displaying all the restaurants that is available from database
 */
public class RestaurantDisplay extends NavigationDrawerUser {
    ListView listViewRestaurant;
    private final String JSONARRAYRestaurant = "Restaurant";
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
        String JSONStringRestaurant = intent.getStringExtra("JSONStringRestaurant");
        ConvertJSON convertJSON = new ConvertJSON(JSONStringRestaurant, JSONARRAYRestaurant);

        //Value in TreeMap is the following order
        //name, address, phoneNumber, cuisine, priceRange, imageURL
        restaurantTreeMap = convertJSON.getTreeMap();

        //Convert treemap into object array to pass into adapter
        Collection<ArrayList<String>> collection = restaurantTreeMap.values();
        restaurantValues = collection.toArray();

        //set adapter for listview
        listViewRestaurant.setAdapter(new RestaurantDisplayAdapter(RestaurantDisplay.this, restaurantValues));

        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> restaurantDetailArray = (ArrayList<String>) restaurantValues[position];
                //go to restaurant details class when user click on the restaurant
                Intent restaurantDetail = new Intent(RestaurantDisplay.this,RestaurantDetail.class);
                //pass the arraylist so restaurant detail so we can display the information according
                //to what the user selected
                restaurantDetail.putExtra("ClickRestaurant", restaurantDetailArray);
                RestaurantDisplay.this.startActivity(restaurantDetail);
            }
        });
    }
}
