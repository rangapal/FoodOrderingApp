package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JeffChoi on 19/09/2016.
 */
public class TotalPriceDisplay extends AppCompatActivity {
    private ListView listViewTotalPrice;
    private TextView totalPriceNumber;
    private ArrayList<ArrayList<String>> totalPriceInformation;

    public float getTotalPrice(ArrayList<String> stringPrice, ArrayList<String> StringQuantity){
        float totalPrice = 0f;

        for(int i = 0; i < stringPrice.size(); i++){
            float value = Float.parseFloat(stringPrice.get(i));
            float quantityValue = Float.parseFloat(StringQuantity.get(i));
            totalPrice += (value*quantityValue);
        }

        return totalPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_list_view);

        Intent intent = getIntent();
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPriceInformation = (ArrayList<ArrayList<String>>) intent.getSerializableExtra("SelectedMenu");

        ArrayList<String> stringPrice = new ArrayList<>();
        ArrayList<String> stringQuantity = new ArrayList<>();

        for(int i = 0; i < totalPriceInformation.size(); i++){
            stringPrice.add(totalPriceInformation.get(i).get(1));
            stringQuantity.add(totalPriceInformation.get(i).get(6));
        }


        totalPriceNumber = (TextView) findViewById(R.id.textViewTotalPriceGridViewPriceNumber);
        String totalPriceNumberInString= Float.toString(getTotalPrice(stringPrice, stringQuantity));
        totalPriceNumber.setText(totalPriceNumberInString);

        listViewTotalPrice = (ListView) findViewById(R.id.listViewTotalPrice);
        listViewTotalPrice.setAdapter(new TotalPriceDisplayAdapter(TotalPriceDisplay.this, totalPriceInformation));

        listViewTotalPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getApplicationContext(), "click one item : "+position, Toast.LENGTH_SHORT).show();
                // Menu class is the container for the intent.
                Menu menu = new Menu(totalPriceInformation.get(position).get(0),
                        totalPriceInformation.get(position).get(1),
                        totalPriceInformation.get(position).get(6),
                        totalPriceInformation.get(position).get(2),
                        totalPriceInformation.get(position).get(3));
                Intent totalPriceDetail = new Intent(TotalPriceDisplay.this, TotalPriceDetail.class);
                totalPriceDetail.putExtra("ClickTotalPriceList", menu);
                startActivity(totalPriceDetail);
            }
        });

        listViewTotalPrice.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "click Long one item : "+position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}