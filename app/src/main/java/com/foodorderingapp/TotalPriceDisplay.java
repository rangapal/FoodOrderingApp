package com.foodorderingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
    private BaseAdapter adapter;
    ArrayList<String> stringPrice;
    ArrayList<String> stringQuantity;
    ArrayList<String> totalPriceDetail;

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


        this.setTitle("Total Price page");
        totalPriceNumber = (TextView) findViewById(R.id.textViewTotalPriceGridViewPriceNumber);
        listViewTotalPrice = (ListView) findViewById(R.id.listViewTotalPrice);

        setDetail();

        listViewTotalPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(getApplicationContext(), "click one item : "+position, Toast.LENGTH_SHORT).show();

                totalPriceDetail = totalPriceInformation.get(position);

                Intent totalPriceDetailintent = new Intent(TotalPriceDisplay.this, TotalPriceDetail.class);
                totalPriceDetailintent.putExtra("ClickTotalPriceList", totalPriceDetail);
                startActivity(totalPriceDetailintent);
            }
        });

        listViewTotalPrice.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // to call method to delete an item from list view.

                int currentPosition = position;

                removeItemFromList(position);
                Toast.makeText(getApplicationContext(), "the menu is deleted", Toast.LENGTH_SHORT).show();
                return true;
            }


        });
    }

    public void setDetail(){
        //this is an arrayList of arraylist of String
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPriceInformation = GlobalVariable.selectedMenuQuantity;

        stringPrice = new ArrayList<>();
        stringQuantity = new ArrayList<>();

        //loop to update all the menu informations
        for(int i = 0; i < totalPriceInformation.size(); i++){
            stringPrice.add(totalPriceInformation.get(i).get(1)); // 1 here is the menuPrice for selected menu
            stringQuantity.add(totalPriceInformation.get(i).get(6));// 6 here is the quantity for selected menu
        }

        //update the total price and display it
        String totalPriceNumberInString= Float.toString(getTotalPrice(stringPrice, stringQuantity));
        totalPriceNumber.setText(totalPriceNumberInString);

        //update the adapter
        adapter = new TotalPriceDisplayAdapter(TotalPriceDisplay.this, totalPriceInformation);
        listViewTotalPrice.setAdapter(adapter);
    }

    //refresh the listview when menu quantity is changed
    @Override
    public void onRestart(){
        super.onRestart();
        setDetail();
    }

    // to delete an item from listView
    protected void removeItemFromList(int position) {

        AlertDialog.Builder alert = new AlertDialog.Builder(TotalPriceDisplay.this);

        final int deletePosition = position;

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this menu?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GlobalVariable.selectedMenuQuantity.remove(deletePosition);

                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            }
        });

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }

}