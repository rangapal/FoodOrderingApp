package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.Profile;

import java.util.ArrayList;

/**
 * A class that show a page for first time login user only.
 * It asks user to input their information and save it to database
 */

public class FirstTimeUser extends AppCompatActivity {
    Button buttonSave;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextAddress;
    EditText editTextAge;
    private String permission;
    private String ID;
    private String url = "http://aaacars.co.nz/writeToUserData.php"; //url to connect and write to DB
    private ArrayList<String> columnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_user);

        //assign id to toolbar variable and set the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSave = (Button)findViewById(R.id.buttonSaveFirstTimeUser);
        editTextFirstName = (EditText) findViewById(R.id.etFirstTimeUserFirstName);
        editTextLastName = (EditText) findViewById(R.id.etFirstTimeUserLastName);
        editTextAddress = (EditText) findViewById(R.id.etFirstTimeUserAddress);
        editTextAge = (EditText) findViewById(R.id.etFirstTimeUserAge);

        this.setTitle("New User Details");//set the title of activity

        Profile profile = Profile.getCurrentProfile();
        ID = profile.getId().toString();//get ID from facebook
        permission = "user";
        //this arraylist is use to match the name of variable on the php file in database
        columnName = new ArrayList<>();
        columnName.add("id");
        columnName.add("firstName");
        columnName.add("lastName");
        columnName.add("age");
        columnName.add("address");
        columnName.add("permission");
    }

    //when user click on save button, save the input data to database
    public void onClick(View view) {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();
        String address = editTextAddress.getText().toString();

        //check for null/empty user input
        if(isUserInputCorrectly(firstName,editTextFirstName) && isUserInputCorrectly(lastName, editTextLastName)
                && isUserInputCorrectly(age, editTextAge) && isUserInputCorrectly(address,editTextAddress)) {
            //this arraylist is for matching the variable name on database so it can be written to database
            ArrayList<String> dataToWrite = new ArrayList<>();
            dataToWrite.add(ID);
            dataToWrite.add(firstName);
            dataToWrite.add(lastName);
            dataToWrite.add(age);
            dataToWrite.add(address);
            dataToWrite.add(permission);

            WriteToDatabase writeToDatabase = new WriteToDatabase(columnName, dataToWrite, url, FirstTimeUser.this);
            writeToDatabase.write();

            Intent intent = new Intent(FirstTimeUser.this, CustomerHomePage.class);
            startActivity(intent);
        }
    }

    //check for empty string with user input
    protected boolean isUserInputCorrectly(String string, EditText editText){
        if(TextUtils.isEmpty(string)) {
            editText.setError("The item cannot be empty");
            return false;
        }else{
            return true;
        }
    }
}
