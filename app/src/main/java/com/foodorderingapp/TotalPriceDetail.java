package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TotalPriceDetail extends AppCompatActivity implements View.OnClickListener {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewPrice;
    ImageView imageViewIcon;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonOrder;
    String menuName;
    String menuPrice;
    String menuDescription;
    EditText editTextQuantity;
    ArrayList<String> totalPrieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_detail);

        Intent intent = getIntent();

        //Value is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID, quantity
        totalPrieDetail = intent.getStringArrayListExtra("ClickTotalPriceList");
        this.setTitle(totalPrieDetail.get(4));

        editTextQuantity = (EditText) findViewById(R.id.editTextTotalPriceDetailQuantity);
        buttonPlus = (Button) findViewById(R.id.buttonForPlus);
        buttonMinus = (Button) findViewById(R.id.buttonForMinus);
        buttonOrder = (Button) findViewById(R.id.buttonTotalPriceDetailOrder);
        textViewName = (TextView)findViewById(R.id.textViewTotalPriceDetailName);
        textViewDescription = (TextView) findViewById(R.id.textViewTotalPriceDetailDescription);
        imageViewIcon = (ImageView)findViewById(R.id.imageViewToTalPriceDetail);
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
        editTextQuantity.setText(GlobalVariable.menuItemQuantity.get(menuName));

        //set logo of menu
        Picasso.with(TotalPriceDetail.this)
                .load(totalPrieDetail.get(3))
                .fit()
                .centerInside()
                .into(imageViewIcon);

    }
    @Override
    public void onClick(View v) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        if(v == buttonPlus){
            quantity++;
            editTextQuantity.setText(Integer.toString(quantity));

        }

        else if(v == buttonMinus){
            if (quantity>0){
                quantity--;
                editTextQuantity.setText(Integer.toString(quantity));
            }
        }

        //when confirm to order is pressed, change the screen back to menu display
        else if(v == buttonOrder){
            GlobalVariable.menuItemQuantity.put(menuName,Integer.toString(quantity));
            updateGlobalSelectedQuantity(quantity);
            onBackPressed();
            finish();
        }
    }

    //method to update the selectedQuantity variable in GlobalVariable class
    private void updateGlobalSelectedQuantity(int quantity){
        for(int i = 0; i < GlobalVariable.selectedMenuQuantity.size();i++){
            if(GlobalVariable.selectedMenuQuantity.get(i).get(0).equals(menuName)){
                if(quantity == 0) // if quantity is zero then remove the arraylist from selectedMenuQuantity
                GlobalVariable.selectedMenuQuantity.remove(i);
                else // replace the quantity value according to changes made by user
                GlobalVariable.selectedMenuQuantity.get(i).set(6, editTextQuantity.getText().toString());
            }
        }
    }

}