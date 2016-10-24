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

/*
    A class that display details such as image, name, description and price of the specific menu
    user can also change the quantity of the menu
 */
public class MenuDetail extends NavigationDrawerUser implements View.OnClickListener {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewPrice;
    ImageView imageViewIcon;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonOrder;
    EditText editTextQuantity;
    private String menuName;
    private ArrayList<String> menuDetails;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail);

        navigation_drawer();//set the toolbar and navigation drawer

        Intent intent = getIntent();
        //Value index is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
        menuDetails = intent.getStringArrayListExtra("ClickMenu");
        menuName = menuDetails.get(0);
        String menuPrice = menuDetails.get(1);
        String menuDescription = menuDetails.get(2);
        this.setTitle(menuName);

        editTextQuantity = (EditText) findViewById(R.id.editTextMenuDetailQuantity);
        buttonPlus = (Button) findViewById(R.id.buttonPlusMenuDetail);
        buttonMinus = (Button) findViewById(R.id.buttonMinusMenuDetail);
        buttonOrder = (Button) findViewById(R.id.buttonMenuDetailOrder);
        textViewName = (TextView)findViewById(R.id.textViewMenuDetailName);
        textViewDescription = (TextView) findViewById(R.id.textViewMenuDetailDescription);
        imageViewIcon = (ImageView)findViewById(R.id.imageViewMenuDetail);
        textViewPrice = (TextView) findViewById(R.id.textViewMenuDetailPrice);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);

        //set the values and display to screen
        textViewName.setText(menuName);
        textViewPrice.setText("$ "+ menuPrice);
        textViewDescription.setText(menuDescription);
        editTextQuantity.setText(GlobalVariable.allMenuItemsQuantity.get(menuName));

        //set logo of menu
        Picasso.with(MenuDetail.this)
                .load(menuDetails.get(3)) //3 is the index for image URL
                .fit()
                .centerInside()
                .into(imageViewIcon);
    }

    //method when user click on the minus,plus and order button
    public void onClick(View view) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        if(view == buttonPlus){//increase the quantity value by one when plus button is click
            quantity++;
            editTextQuantity.setText(Integer.toString(quantity));
        }

        else if(view == buttonMinus){//decrease the quantity value by one when minus button is click
            if (quantity>0){
                quantity--;
                editTextQuantity.setText(Integer.toString(quantity));
            }
        }

        //when confirm to order is pressed, change the screen back to MenuDisplay class
        else if(view == buttonOrder){
            GlobalVariable.allMenuItemsQuantity.put(menuName,Integer.toString(quantity));
            onBackPressed();
            finish();
        }
    }
}
