package com.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuDisplayAdapter extends BaseAdapter {
    private Object[] restaurantValues;
    private Context context;
    private LayoutInflater inflater;

    public MenuDisplayAdapter(Context context, Object[] restaurantValues){
        this.restaurantValues = restaurantValues;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return restaurantValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Value in ArrayList is the following order
    ////name, address, phoneNumber, cuisine, priceRange, image URL
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView; //this view is used to mimic view from restaurant_grid_view_layout.xml

        //typecast restaurantValues from object to ArrayList
        ArrayList<String> restaurantValueArray = (ArrayList<String>)restaurantValues[position];

        if(convertView == null){
            listView = inflater.inflate(R.layout.menu_grid_view_layout, parent, false);
            TextView textViewName = (TextView) listView.findViewById(R.id.textViewMenuName);
            TextView textViewPrice = (TextView) listView.findViewById(R.id.textViewMenuPrice);
            TextView textViewQuantity = (TextView) listView.findViewById(R.id.textViewMenuQuantity);

            textViewName.setText("helo");
            textViewPrice.setText("osa");
            textViewQuantity.setText("2");
            //textViewName.setText(restaurantValueArray.get(0)); //set name of restaurant
            //textViewDescription.setText(restaurantValueArray.get(3)); //set description of restaurant

        }else {
            listView = convertView;
        }

        //set logo of restaurant
         /*Picasso.with(context)
                .load(restaurantValueArray.get(5))
                .fit() // will explain later
                .centerInside()
                .into((ImageView) listView.findViewById(R.id.imageViewRestaurant));*/

        return listView;
    }


}
