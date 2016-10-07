package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TotalPriceDetail extends AppCompatActivity {
    TextView textViewName;
    TextView textViewPrice;
    EditText editViewQuantity;
    ImageView imageViewIcon;
    Button buttonConfirm;
    int changableQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_price_detail);

        Intent intent = getIntent();

        //Value is the following order
        ////name, price, quantity, image
        Menu totalPriceDetails = (Menu) intent.getSerializableExtra("ClickTotalPriceList");

        textViewName = (TextView)findViewById(R.id.textViewTotalPriceDetailName);
        textViewPrice = (TextView) findViewById(R.id.textViewTotalPriceDetailPriceNumber);
        //editViewQuantity = (EditText) findViewById(R.id.editTextTotalPriceDetailQuantity);
        imageViewIcon = (ImageView) findViewById(R.id.imageViewToTalPriceDetail);
        //buttonConfirm = (Button) findViewById(R.id.buttonTotalPriceDetail);

        //buttonConfirm.setOnClickListener(this);

        textViewName.setText(totalPriceDetails.getName());
        textViewPrice.setText("$ " + totalPriceDetails.getPrice());
        editViewQuantity.setText(totalPriceDetails.getQuantity());
        //imageViewIcon.setImageResource(totalPriceDetails.getImageId());

        changableQuantity = Integer.parseInt(totalPriceDetails.getQuantity());

    }
    // this doesn't work so far...
    public void onButtonConfirm(View v){
        Intent intent = new Intent();
        intent.putExtra("changedQuantity", changableQuantity);
        setResult(RESULT_OK, intent);

        finish();
    }
}