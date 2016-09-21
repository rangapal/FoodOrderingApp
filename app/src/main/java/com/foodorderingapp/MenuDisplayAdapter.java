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
    private Object[] menuValue;
    private Context context;
    private LayoutInflater inflater;

    public MenuDisplayAdapter(Context context, Object[] menuValue){
        this.menuValue = menuValue;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return menuValue.length;
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
    //menuName, menuPrice, menuDescription, menuImage, restaurantName
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView; //this view is used to mimic view from menu_grid_view_layout.xml

        //typecast menuValues from object to ArrayList
        ArrayList<String> menuValueArray = (ArrayList<String>) menuValue[position];

        if(convertView == null){
            listView = inflater.inflate(R.layout.menu_grid_view_layout, parent, false);
            TextView textViewName = (TextView) listView.findViewById(R.id.textViewMenuName);
            TextView textViewPrice = (TextView) listView.findViewById(R.id.textViewMenuPrice);
            TextView textViewQuantity = (TextView) listView.findViewById(R.id.textViewMenuQuantity);


            textViewName.setText(menuValueArray.get(0)); //set name of restaurant
            textViewPrice.setText(menuValueArray.get(1));
            textViewQuantity.setText("0");

        }else {
            listView = convertView;
        }

        //set menu logo of restaurant
         Picasso.with(context)
                .load(menuValueArray.get(3))
                .fit() // will explain later
                .centerInside()
                .into((ImageView) listView.findViewById(R.id.imageViewMenu));

        return listView;
    }


}
