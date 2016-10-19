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

        //set the toolbar and navigation drawer
        navigation_drawer();

        Intent intent = getIntent();
        //Value is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
        menuDetails = intent.getStringArrayListExtra("ClickMenu");
        this.setTitle(menuDetails.get(4));

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

        menuName = menuDetails.get(0);
        String menuPrice = menuDetails.get(1);
        String menuDescription = menuDetails.get(2);

        //set the values and display to screen
        textViewName.setText(menuName);
        textViewPrice.setText("$ "+ menuPrice);
        textViewDescription.setText(menuDescription);
        editTextQuantity.setText(GlobalVariable.menuItemQuantity.get(menuName));

        //set logo of menu
        Picasso.with(MenuDetail.this)
                .load(menuDetails.get(3)) //3 is the index for image URL
                .fit()
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        if(view == buttonPlus){
            quantity++;
            editTextQuantity.setText(Integer.toString(quantity));
        }

        else if(view == buttonMinus){
            if (quantity>0){
                quantity--;
                editTextQuantity.setText(Integer.toString(quantity));
            }
        }

        //when confirm to order is pressed, change the screen back to menu display
        else if(view == buttonOrder){
            GlobalVariable.menuItemQuantity.put(menuName,Integer.toString(quantity));
            onBackPressed();
            finish();
        }
    }
}
