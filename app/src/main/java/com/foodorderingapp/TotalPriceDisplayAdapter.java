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
 * Created by JeffChoi on 19/09/2016.
 */
public class TotalPriceDisplayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArrayList<String>> totalPriceInfor;
    private LayoutInflater inflater;

    public TotalPriceDisplayAdapter(Context context, ArrayList<ArrayList<String>> totalPriceInfor){

        this.context = context;
        this.totalPriceInfor = totalPriceInfor;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {return totalPriceInfor.size();}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView;

        if(convertView == null){
            listView= inflater.inflate(R.layout.total_price_list_view_layout, parent, false);

            //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
            TextView nameMenu = (TextView) listView.findViewById(R.id.textViewNameTotalPriceLayout);
            TextView priceMenu = (TextView) listView.findViewById(R.id.textViewPriceValueTotalPriceLayout);
            TextView quantityMenu = (TextView) listView.findViewById(R.id.textViewQuantityValueTotalPriceLayout);
            //ImageView imageViewMenu = (ImageView) listView.findViewById(R.id.imageViewTotalPriceLayout);

            String menuName = totalPriceInfor.get(position).get(0);
            String menuPrice = totalPriceInfor.get(position).get(1);
            String menuQuantity = totalPriceInfor.get(position).get(6);

            nameMenu.setText(menuName);
            priceMenu.setText("$ "+menuPrice);
            quantityMenu.setText(menuQuantity);

        }else{
            listView = convertView;
        }

        //set menu logo of restaurant
        Picasso.with(context)
                .load(totalPriceInfor.get(position).get(3))
                .fit() // will explain later
                .centerInside()
                .into((ImageView) listView.findViewById(R.id.imageViewTotalPriceLayout));

        return listView;
    }
}
