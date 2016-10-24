package com.foodorderingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class is for displaying the total price of the food that user selected
 */
public class TotalPriceDisplay extends NavigationDrawerUser {
    private ListView listViewTotalPrice;
    private TextView textViewTotalPrice;
    private ArrayList<ArrayList<String>> totalPriceInformation;
    private BaseAdapter adapter;
    ArrayList<String> arrayListMenuPrice;
    ArrayList<String> arrayListMenuQuantity;
    ArrayList<String> totalPriceDetail;
    private final String pressAndHoldToDeletePref = "pressAndHoldToDeleteShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_list_view);

        //set the toolbar and navigation drawer
        navigation_drawer();
        this.setTitle("Order Summary");
        textViewTotalPrice = (TextView) findViewById(R.id.textViewTotalPriceGridViewPriceNumber);
        listViewTotalPrice = (ListView) findViewById(R.id.listViewTotalPrice);

        //method to display the menu items that user ordered
        setDetail();
        //pop up to tell the user how to delete the menu item
        showPressAndHoldToDeleteAlertDialog();

        //listener for when user click the menu item
        listViewTotalPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //arraylist of arraylist of string which is passed on to next activity to be used
                totalPriceDetail = totalPriceInformation.get(position);
                Intent totalPriceDetailintent = new Intent(TotalPriceDisplay.this, TotalPriceDetail.class);
                totalPriceDetailintent.putExtra("ClickTotalPriceList", totalPriceDetail);
                startActivity(totalPriceDetailintent);
            }
        });

        //listener for when user press and hold the menu item
        listViewTotalPrice.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // to call method to delete an item from list view.
                removeItemFromList(position);
                return true;

            }
        });
    }

    //method to set the value to total price and display the menu items that user ordered to screen
    private void setDetail(){
        //this is an ArrayList of Arraylist of String
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPriceInformation = GlobalVariable.userSelectedMenuItemQuantity;

        arrayListMenuPrice = new ArrayList<>();
        arrayListMenuQuantity = new ArrayList<>();

        //loop to update all the menu informations
        for(int i = 0; i < totalPriceInformation.size(); i++){
            arrayListMenuPrice.add(totalPriceInformation.get(i).get(1)); // 1 here is the menuPrice for selected menu
            arrayListMenuQuantity.add(totalPriceInformation.get(i).get(6));// 6 here is the quantity for selected menu
        }

        //update the total price and display it
        String totalPriceNumberInString= Float.toString(getTotalPrice(arrayListMenuPrice, arrayListMenuQuantity));
        textViewTotalPrice.setText("$ "+totalPriceNumberInString);

        //set the menu items if there any changes from TotalPriceDetail class
        adapter = new TotalPriceDisplayAdapter(TotalPriceDisplay.this, totalPriceInformation);
        listViewTotalPrice.setAdapter(adapter);
    }

    //refresh the listview when menu quantity is changed
    @Override
    public void onRestart(){
        super.onRestart();
        setDetail();
    }

    // method to delete a menu item
    private void removeItemFromList(int position) {

        AlertDialog.Builder alert = new AlertDialog.Builder(TotalPriceDisplay.this);
        final int deletePosition = position;

        alert.setTitle("Delete");
        alert.setMessage("Do you want to delete this menu?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deleteMenuName = GlobalVariable.userSelectedMenuItemQuantity.get(deletePosition).get(0);
                //remove the arraylist from userSelectedMenuItemQuantity
                GlobalVariable.userSelectedMenuItemQuantity.remove(deletePosition);
                //set the quantity of the menu to zero
                GlobalVariable.allMenuItemsQuantity.put(deleteMenuName, "0");
                //update the menu items and display it to screen
                setDetail();
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                // if there is no menu after deleting all, then go back to menu display page
                if(GlobalVariable.userSelectedMenuItemQuantity.isEmpty())
                    onBackPressed();
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

    //this method is used for display the information on how to delete the menu item
    //it should only display once
    private void showPressAndHoldToDeleteAlertDialog(){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean pressAndHoldToDeleteShown = mPrefs.getBoolean(pressAndHoldToDeletePref, false);

        if(!pressAndHoldToDeleteShown){
            new AlertDialog.Builder(this).setTitle("Information").setMessage("Press and Hold on the menu item to delete it").setPositiveButton(
                    "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(pressAndHoldToDeletePref, true);
            editor.commit();
        }
    }

    //calculating the totalPrice
    private float getTotalPrice(ArrayList<String> stringPrice, ArrayList<String> StringQuantity){
        float totalPrice = 0f;
        for(int i = 0; i < stringPrice.size(); i++){
            float value = Float.parseFloat(stringPrice.get(i));
            float quantityValue = Float.parseFloat(StringQuantity.get(i));
            totalPrice += (value*quantityValue);
        }
        return totalPrice;
    }

    // to show up the message to customer to say thank you
    public void onButtonForConfirm(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TotalPriceDisplay.this);

        alertDialogBuilder.setTitle("Dear Customer");

        alertDialogBuilder.setMessage("Thank you for ordering menu from our app :)")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
    }
}