package com.foodorderingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JeffChoi on 19/09/2016.
 */
public class TotalPriceDisplayAdapter extends ArrayAdapter<String> {
    Activity context;
    String[] name;
    String[] price;
    String[] quantity;
    Integer[] ImageId;

    public TotalPriceDisplayAdapter(Activity context, String[] name, String[] price,
                                    String[] quantity, Integer[] ImageId){
        super(context, R.layout.total_price_grid_view_layout, name);
        this.context = context;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.ImageId = ImageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.total_price_grid_view_layout, null, true);

        TextView nameMenu = (TextView) rowView.findViewById(R.id.textViewNameTotalPriceLayout);
        TextView priceMenu = (TextView) rowView.findViewById(R.id.textViewPriceTotalPriceLayout);
        TextView quantityMenu = (TextView) rowView.findViewById(R.id.textViewQuantityTotalPriceLayout);
        ImageView imageViewMenu = (ImageView) rowView.findViewById(R.id.imageViewTotalPriceLayout);

        nameMenu.setText(name[position]);
        priceMenu.setText(price[position]);
        quantityMenu.setText(quantity[position]);
        imageViewMenu.setImageResource(ImageId[position]);

        return rowView;
    }
}
