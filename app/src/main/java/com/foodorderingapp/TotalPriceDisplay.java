package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JeffChoi on 19/09/2016.
 */
public class TotalPriceDisplay extends AppCompatActivity {
    private ListView listViewTotalPrice;
    private float totalPrice;

    String[] name = {
            "Burger",
            "Spaghetti",
            "Ramen",
            "Pizza",
            "Bibimbap",
            "Mapo Tofu"
    };

    String[] price = {
            "10",
            "11",
            "12",
            "13",
            "14",
            "15"
    };

    String[] quantity = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
    };
    Integer[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice(String[] stringPrice, String[] StringQuantityValue){
        float totalPrice = 0f;

        for(int i = 0; i < stringPrice.length; i++){
            float value = Float.parseFloat(stringPrice[i]);
            float quantityValue = Float.parseFloat(StringQuantityValue[i]);
            totalPrice += (value*quantityValue);
        }

        return totalPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_list_view);

        TextView totalPriceNumber = (TextView) findViewById(R.id.textViewTotalPriceGridViewPriceNumber);
        String totalPriceNumberInString= Float.toString(getTotalPrice(price, quantity));
        totalPriceNumber.setText(totalPriceNumberInString);

        listViewTotalPrice = (ListView) findViewById(R.id.listViewTotalPrice);
        TotalPriceDisplayAdapter adapter = new TotalPriceDisplayAdapter(this, name, price, quantity, imageId);
        listViewTotalPrice.setAdapter(adapter);
        listViewTotalPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Menu class is the container for the intent.
                Menu menu = new Menu(name[position], price[position], quantity[position], imageId[position]);
                Intent totalPriceDetail = new Intent(TotalPriceDisplay.this, TotalPriceDetail.class);
                totalPriceDetail.putExtra("ClickTotalPriceList", menu);
                startActivity(totalPriceDetail);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            int changedQuantity = data.getIntExtra("changedQuantity", 0);
            Toast.makeText(this, "Quantity is : " + changedQuantity, Toast.LENGTH_LONG).show();
        }else{
            int changedQuantity = data.getIntExtra("changedQuantity", 0);
            Toast.makeText(this, "There is no value to get", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}