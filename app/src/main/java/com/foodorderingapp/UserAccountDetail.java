package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by User on 10/3/2016.
 */

public class UserAccountDetail extends NavigationDrawerUser {
    private final String JSONARRAY = "User";
    private TreeMap<String, ArrayList<String>> userAccountDetailTreeMap;
    Object[] user;
    private ArrayList<String> userAccountDetailValues;

    Button buttonEdit;
    Button buttonSave;
    Button buttonLogOut;

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextAddress;
    EditText editTextAge;

    TextView textViewFirstName;
    TextView textViewLastName;
    TextView textViewAddress;
    TextView textViewAge;

    private String permission;
    private String ID;
    private String url = "http://aaacars.co.nz/writeToUserData.php"; //url to connect and write to database
    private ArrayList<String> columnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account_detail);

        //set the toolbar and navigation drawer
        navigation_drawer();

        // set each view or layout by id
        buttonSave = (Button)findViewById(R.id.buttonSaveUserAccountDetail);
        buttonEdit = (Button)findViewById(R.id.buttonEditUserAccountDetail);
        buttonLogOut = (Button)findViewById(R.id.buttonLogOutUserAccountDetail);
        editTextFirstName = (EditText) findViewById(R.id.etUserAccountDetailFirstName);
        editTextLastName = (EditText) findViewById(R.id.etUserAccountDetailLastName);
        editTextAddress = (EditText) findViewById(R.id.etUserAccountDetailAddress);
        editTextAge = (EditText) findViewById(R.id.etUserAccountDetailAge);
        textViewFirstName = (TextView) findViewById(R.id.tvUserAccountFirstName);
        textViewLastName = (TextView) findViewById(R.id.tvUserAccountLastName);
        textViewAddress = (TextView) findViewById(R.id.tvUserAccountAddress);
        textViewAge = (TextView) findViewById(R.id.tvUserAccountAge);

        //set the title of activity
        this.setTitle("User Account Details");

        Profile profile = Profile.getCurrentProfile();
        //get ID from facebook
        ID = profile.getId().toString();

        //this method set userAccountDetailValues with the current user details
        getUserDetail();

        //display user details to screen
        textViewFirstName.setText(userAccountDetailValues.get(1));
        textViewLastName.setText(userAccountDetailValues.get(2));
        textViewAge.setText(userAccountDetailValues.get(3));
        textViewAddress.setText(userAccountDetailValues.get(4));

        editTextFirstName.setText(userAccountDetailValues.get(1));
        editTextLastName.setText(userAccountDetailValues.get(2));
        editTextAge.setText(userAccountDetailValues.get(3));
        editTextAddress.setText(userAccountDetailValues.get(4));

        permission = userAccountDetailValues.get(5);


        //this arraylist is use to match the name of variable on the php file in database
        columnName = new ArrayList<>();
        columnName.add("id");
        columnName.add("firstName");
        columnName.add("lastName");
        columnName.add("age");
        columnName.add("address");
        columnName.add("permission");
    }

    //method to convert the JSONString of user and extract the correct user details to be used
    public void getUserDetail(){
        //convert JSONString into treemap for easy access and manipulation
        Intent intent = getIntent();
        String JSONString = intent.getStringExtra("JSONStringForUserDetail");
        ConvertJSON convertJSON = new ConvertJSON(JSONString,JSONARRAY);

        //Value in TreeMap is the following order
        //id, firstName, lastName, age, address, permission
        userAccountDetailValues = convertJSON.getTreeMap().get(ID);
    }

    public void setVisiblity(int textViewVisibility, int editViewVisibility){

        //set visibility of textview, editButton and LogOutButton
        textViewFirstName.setVisibility(textViewVisibility);
        textViewLastName.setVisibility(textViewVisibility);
        textViewAddress.setVisibility(textViewVisibility);
        textViewAge.setVisibility(textViewVisibility);
        buttonEdit.setVisibility(textViewVisibility);
        buttonLogOut.setVisibility(textViewVisibility);

        //set visibility of editText and saveButton
        editTextFirstName.setVisibility(editViewVisibility);
        editTextLastName.setVisibility(editViewVisibility);
        editTextAddress.setVisibility(editViewVisibility);
        editTextAge.setVisibility(editViewVisibility);
        buttonSave.setVisibility(editViewVisibility);
    }

    // when click the edit button, then all textView, placed right side, and edit button
    // are unvisible then editTexts and save button show up to user.
    public void onButtonForEdit(View view) {
        // textViews, edit button and Log out button become invisible.
        // editTexts and save button show up to user.
        setVisiblity(View.INVISIBLE, View.VISIBLE);
    }

    //when user Click on save button
    public void onButtonForSave(View view) {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();
        String address = editTextAddress.getText().toString();

        //check for null/empty user input
        if(isUserInputCorrectly(firstName,editTextFirstName) && isUserInputCorrectly(lastName, editTextLastName)
                && isUserInputCorrectly(age, editTextAge) && isUserInputCorrectly(address,editTextAddress)){
            //this arraylist is for saving the value that need to write to database
            ArrayList<String> dataToWrite = new ArrayList<>();
            dataToWrite.add(ID);
            dataToWrite.add(firstName);
            dataToWrite.add(lastName);
            dataToWrite.add(age);
            dataToWrite.add(address);
            dataToWrite.add(permission);

            //connect and write data to database
            WriteToDatabase writeToDatabase = new WriteToDatabase(columnName, dataToWrite, url, UserAccountDetail.this);
            writeToDatabase.write();

            //update the text and display to screen
            textViewFirstName.setText(firstName);
            textViewLastName.setText(lastName);
            textViewAge.setText(age);
            textViewAddress.setText(address);

            // textViews, edit button and Log out button show up to user.
            // editTexts and save button become invisible.
            setVisiblity(View.VISIBLE, View.INVISIBLE);
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

    //log out when click on log out button
    public void onButtonForLogOut(View view){
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(UserAccountDetail.this,Login.class);
        startActivity(intent);
    }

}
