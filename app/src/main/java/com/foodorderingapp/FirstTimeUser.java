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
 * A page that show up only for first time login user.
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
    private String url = "http://aaacars.co.nz/writeToUserData.php"; //url to connect with database
    private ArrayList<String> columnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_user);

        //set the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSave = (Button)findViewById(R.id.buttonSaveFirstTimeUser);
        editTextFirstName = (EditText) findViewById(R.id.etFirstTimeUserFirstName);
        editTextLastName = (EditText) findViewById(R.id.etFirstTimeUserLastName);
        editTextAddress = (EditText) findViewById(R.id.etFirstTimeUserAddress);
        editTextAge = (EditText) findViewById(R.id.etFirstTimeUserAge);

        //set the title of activity
        this.setTitle("New User Details");

        Profile profile = Profile.getCurrentProfile();
        //get ID from facebook
        ID = profile.getId().toString();
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

    //when user click on save button
    public void onClick(View view) {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();
        String address = editTextAddress.getText().toString();

        //check for null/empty user input
        if(isUserInputCorrectly(firstName,editTextFirstName) && isUserInputCorrectly(lastName, editTextLastName)
                && isUserInputCorrectly(age, editTextAge) && isUserInputCorrectly(address,editTextAddress)) {
            //this arraylist is for saving the value that need to write to database
            ArrayList<String> dataToWrite = new ArrayList<>();
            dataToWrite.add(ID);
            dataToWrite.add(firstName);
            dataToWrite.add(lastName);
            dataToWrite.add(age);
            dataToWrite.add(address);
            dataToWrite.add(permission);

            WriteToDatabase writeToDatabase = new WriteToDatabase(columnName, dataToWrite, url, FirstTimeUser.this);
            writeToDatabase.write();

            //start new activity after writing to database
            Intent intent = new Intent(FirstTimeUser.this, testClass.class);
            startActivity(intent);
        }
    }

    //check for empty string with user input
    public boolean isUserInputCorrectly(String string, EditText editText){
        if(TextUtils.isEmpty(string)) {
            editText.setError("The item cannot be empty");
            return false;
        }else{
            return true;
        }
    }
}
