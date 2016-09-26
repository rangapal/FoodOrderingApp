package com.foodorderingapp;

import android.app.Activity;
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

import static com.foodorderingapp.R.*;


public class MenuDetail extends AppCompatActivity implements View.OnClickListener {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewPrice;
    ImageView imageViewIcon;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonOrder;
    Button buttonOrderSummary;
    EditText editTextQuantity;
    ArrayList<String> menuDetails;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.menu_detail);

        Intent intent = getIntent();
        //Value is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
        menuDetails = intent.getStringArrayListExtra("ClickMenu");
        this.setTitle(menuDetails.get(4));
        //String quantity = intent.getStringExtra("Quantity");

        editTextQuantity = (EditText) findViewById(R.id.editTextMenuDetailQuantity);
        buttonPlus = (Button) findViewById(R.id.buttonPlusMenuDetail);
        buttonMinus = (Button) findViewById(R.id.buttonMinusMenuDetail);
        buttonOrder = (Button) findViewById(id.buttonMenuDetailOrder);
        textViewName = (TextView)findViewById(R.id.textViewMenuDetailName);
        textViewDescription = (TextView) findViewById(R.id.textViewMenuDetailDescription);
        imageViewIcon = (ImageView)findViewById(id.imageViewMenuDetail);
        textViewPrice = (TextView) findViewById(id.textViewMenuDetailPrice);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);

        String menuName = menuDetails.get(0);
        String menuPrice = menuDetails.get(1);
        String menuDescription = menuDetails.get(2);

        textViewName.setText(menuName);
        textViewPrice.setText(menuPrice);
        textViewDescription.setText(menuDescription);
        editTextQuantity.setText("0");

        //editTextQuantity.setText(GlobalVariable.menuItemQuantity.get(menuName));

        //set logo of menu
        Picasso.with(MenuDetail.this)
                .load(menuDetails.get(3))
                .fit() // will explain later
                .centerInside()
                .into(imageViewIcon);
    }

    public void onClick(View view) {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString());

        if(view == buttonPlus){
            quantity++;
            editTextQuantity.setText(Integer.toString(quantity));

        }
        if(view == buttonMinus){
            if (quantity>0){
                quantity--;
                editTextQuantity.setText(Integer.toString(quantity));
            }
        }

        if(view == buttonOrder){
//            Intent returnIntent = new Intent();
//            returnIntent.putExtra("Quantity",Integer.toString(quantity));
//            setResult(Activity.RESULT_OK,returnIntent);
//            finish();
        }

        //Intent newIntent = new Intent(MenuDetail.this, testClass.class);
        //GetJSON getJSON = new GetJSON(MenuDetail.this,"http://aaacars.co.nz/getMenu.php",testClass.class);
        //MenuDetail.this.startActivity(newIntent);
    }


}
