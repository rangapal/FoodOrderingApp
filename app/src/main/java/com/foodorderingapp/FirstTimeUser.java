package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.Profile;

import java.util.ArrayList;

/**
 * Created by User on 10/3/2016.
 */

public class FirstTimeUser extends AppCompatActivity {
    Button buttonSave;
    EditText editTextName;
    EditText editTextAddress;
    EditText editTextAge;
    String permission;
    String ID;
    String url = "http://aaacars.co.nz/writeToUserData.php";
    ArrayList<String> columnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_user);
        buttonSave = (Button)findViewById(R.id.buttonSaveUserDetail);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextName = (EditText) findViewById(R.id.editTextName);

        Profile profile = Profile.getCurrentProfile();
        ID = profile.getId();
        permission = "user";

        columnName = new ArrayList<>();
        columnName.add("id");
        columnName.add("firstName");
        columnName.add("lastName");
        columnName.add("age");
        columnName.add("address");
        columnName.add("permission");
    }

    public void onClick(View view) {
        ArrayList<String> dataToWrite = new ArrayList<>();
        dataToWrite.add(ID);
        dataToWrite.add(editTextName.getText().toString());
        //Log.d("Text is", editTextName.getText().toString());
        dataToWrite.add("there");
        dataToWrite.add(editTextAge.getText().toString());
        dataToWrite.add(editTextAddress.getText().toString());
        dataToWrite.add(permission);

        WriteToDatabase writeToDatabase = new WriteToDatabase(columnName,dataToWrite,url,FirstTimeUser.this);
        writeToDatabase.write();

        Intent intent = new Intent(FirstTimeUser.this, testClass.class);
        startActivity(intent);
    }
}
