package com.foodorderingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class test extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.buttonGet);

    }

    public void onClick(View view) {
        GetJSON getJSON = new GetJSON(test.this,"http://aaacars.co.nz/getRestaurant.php",RestaurantDisplay.class);

        //String s = getJSON.getJsonString();
    }
}
