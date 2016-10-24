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

/**
 * An adapter class to populate user selected menu items to TotalPriceDisplay class
 */
public class TotalPriceDisplayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArrayList<String>> totalPriceInfo;
    private LayoutInflater inflater;

    public TotalPriceDisplayAdapter(Context context, ArrayList<ArrayList<String>> totalPriceInfo){
        this.context = context;
        this.totalPriceInfo = totalPriceInfo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {return totalPriceInfo.size();}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

    //Value in ArrayList of ArrayList is the following order
    //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView; //this view is used to mimic view from total_price_list_view_layout.xml

        if(convertView == null){
            listView= inflater.inflate(R.layout.total_price_list_view_layout, parent, false);
            TextView txMenuName = (TextView) listView.findViewById(R.id.textViewNameTotalPriceLayout);
            TextView txMenuPrice = (TextView) listView.findViewById(R.id.textViewPriceValueTotalPriceLayout);
            TextView txMenuQuantity = (TextView) listView.findViewById(R.id.textViewQuantityValueTotalPriceLayout);

            //position is refer to each of the menu items
            //0 is index for name, 1 is index for price, 6 is index for quantity
            String menuName = totalPriceInfo.get(position).get(0);
            String menuPrice = totalPriceInfo.get(position).get(1);
            String menuQuantity = totalPriceInfo.get(position).get(6);
            txMenuName.setText(menuName);
            txMenuPrice.setText("$ "+menuPrice);
            txMenuQuantity.setText(menuQuantity);
        }else{
            listView = convertView;
        }
        //set logo of menu
        Picasso.with(context)
                .load(totalPriceInfo.get(position).get(3))
                .fit()
                .centerInside()
                .into((ImageView) listView.findViewById(R.id.imageViewTotalPriceLayout));
        return listView;
    }
}
