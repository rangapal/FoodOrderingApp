package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JeffChoi on 19/09/2016.
 */
public class TotalPriceDisplay extends AppCompatActivity {
    private ListView listViewTotalPrice;
    private TextView totalPriceNumber;
    private ArrayList<ArrayList<String>> totalPriceInformation;
    ArrayList<String> stringPrice;
    ArrayList<String> stringQuantity;

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



        totalPriceNumber = (TextView) findViewById(R.id.textViewTotalPriceGridViewPriceNumber);


        listViewTotalPrice = (ListView) findViewById(R.id.listViewTotalPrice);

        setDetail();

//        listViewTotalPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(getApplicationContext(), "click one item : "+position, Toast.LENGTH_SHORT).show();
//                // Menu class is the container for the intent.
//                Menu menu = new Menu(totalPriceInformation.get(position).get(0),
//                        totalPriceInformation.get(position).get(1),
//                        totalPriceInformation.get(position).get(6),
//                        totalPriceInformation.get(position).get(2),
//                        totalPriceInformation.get(position).get(3));
//                Intent totalPriceDetail = new Intent(TotalPriceDisplay.this, TotalPriceDetail.class);
//                totalPriceDetail.putExtra("ClickTotalPriceList", menu);
//                startActivity(totalPriceDetail);
//            }
//        });
//
//        listViewTotalPrice.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "click Long one item : "+position, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
    }

    public void setDetail(){
        Intent intent = getIntent();
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPriceInformation = (ArrayList<ArrayList<String>>) intent.getSerializableExtra("SelectedMenu");

        stringPrice = new ArrayList<>();
        stringQuantity = new ArrayList<>();

        for(int i = 0; i < totalPriceInformation.size(); i++){
            stringPrice.add(totalPriceInformation.get(i).get(1));
            stringQuantity.add(totalPriceInformation.get(i).get(6));
        }

        String totalPriceNumberInString= Float.toString(getTotalPrice(stringPrice, stringQuantity));
        totalPriceNumber.setText(totalPriceNumberInString);
        listViewTotalPrice.setAdapter(new TotalPriceDisplayAdapter(TotalPriceDisplay.this, totalPriceInformation));
    }

    //refresh the listview when menu quantity is changed
    @Override
    public void onRestart(){
        super.onRestart();
        setDetail();
    }

}