package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This class is for displaying the menu item that user selected from the TotalPriceDisplay class
 */
public class TotalPriceDetail extends NavigationDrawerUser implements View.OnClickListener {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewPrice;
    ImageView imageViewMenuIcon;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonOrder;
    String menuName;
    String menuPrice;
    String menuDescription;
    EditText editTextQuantity;
    private ArrayList<String> totalPrieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_detail);

        //set the toolbar and navigation drawer
        navigation_drawer();

        Intent intent = getIntent();
        //Value is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPrieDetail = intent.getStringArrayListExtra("ClickTotalPriceList");
        this.setTitle(totalPrieDetail.get(4));//set menu name as screen title

        editTextQuantity = (EditText) findViewById(R.id.editTextTotalPriceDetailQuantity);
        buttonPlus = (Button) findViewById(R.id.buttonPlusTotalPriceDetail);
        buttonMinus = (Button) findViewById(R.id.buttonMinusTotalPriceDetail);
        buttonOrder = (Button) findViewById(R.id.buttonTotalPriceDetailOrder);
        textViewName = (TextView)findViewById(R.id.textViewTotalPriceDetailName);
        textViewDescription = (TextView) findViewById(R.id.textViewTotalPriceDetailDescription);
        imageViewMenuIcon = (ImageView)findViewById(R.id.imageViewToTalPriceDetail);
        textViewPrice = (TextView) findViewById(R.id.textViewTotalPriceDetailPriceNumber);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);

        menuName = totalPrieDetail.get(0);
        menuPrice = totalPrieDetail.get(1);
        menuDescription = totalPrieDetail.get(2);

        textViewName.setText(menuName);
        textViewPrice.setText("$" + menuPrice);
        textViewDescription.setText(menuDescription);
        editTextQuantity.setText(GlobalVariable.allMenuItemsQuantity.get(menuName));

        //set logo of menu
        Picasso.with(TotalPriceDetail.this)
                .load(totalPrieDetail.get(3))
                .fit()
                .centerInside()
                .into(imageViewMenuIcon);

    }

    @Override
    public void onClick(View v) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        if(v == buttonPlus){//increase the quantity value by one when plus button is click
            quantity++;
            editTextQuantity.setText(Integer.toString(quantity));

        }

        else if(v == buttonMinus){//decrease the quantity value by one when minus button is click
            if (quantity>0){
                quantity--;
                editTextQuantity.setText(Integer.toString(quantity));
            }
        }

        //when confirm to order is pressed, change the screen back to TotalPriceDisplay screen
        else if(v == buttonOrder){
            GlobalVariable.allMenuItemsQuantity.put(menuName,Integer.toString(quantity));
            updateUserSelectedMenuItemQuantity(quantity);
            onBackPressed();
            finish();
        }
    }

    //method to update the UserSelectedMenuItemQuantity variable in GlobalVariable class
    private void updateUserSelectedMenuItemQuantity(int quantity){
        for(int i = 0; i < GlobalVariable.userSelectedMenuItemQuantity.size(); i++){
            if(GlobalVariable.userSelectedMenuItemQuantity.get(i).get(0).equals(menuName)){
                if(quantity == 0) // if quantity is zero then remove the arraylist from userSelectedMenuItemQuantity
                GlobalVariable.userSelectedMenuItemQuantity.remove(i);
                else // replace the quantity value according to changes made by user
                GlobalVariable.userSelectedMenuItemQuantity.get(i).set(6, editTextQuantity.getText().toString());
            }
        }
    }

}