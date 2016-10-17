package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.Profile;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by User on 10/3/2016.
 */

public class UserAccountDetail extends AppCompatActivity {
    private final String JSONARRAY = "User";
    private TreeMap<String, ArrayList<String>> userAccountDetailTreeMap;
    Object[] user;
    private ArrayList<String> userAccountDetailValues;

    Button buttonEdit;
    Button buttonSave;

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextAddress;
    EditText editTextAge;

    TextView textViewFirstName;
    TextView textViewLastName;
    TextView textViewAddress;
    TextView textViewAge;

    String permission;
    String ID;
    String url = "http://aaacars.co.nz/writeToUserData.php"; //url to connect with database
    ArrayList<String> columnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account_detail);

        // allocate each view or layout by id
        buttonSave = (Button)findViewById(R.id.buttonSaveUserAccountDetail);
        buttonEdit = (Button)findViewById(R.id.buttonEditUserAccountDetail);
        editTextFirstName = (EditText) findViewById(R.id.etUserAccountDetailFirstName);
        editTextLastName = (EditText) findViewById(R.id.etUserAccountDetailLastName);
        editTextAddress = (EditText) findViewById(R.id.etUserAccountDetailAddress);
        editTextAge = (EditText) findViewById(R.id.etUserAccountDetailAge);
        textViewFirstName = (TextView) findViewById(R.id.tvUserAccountFirstName);
        textViewLastName = (TextView) findViewById(R.id.tvUserAccountLastName);
        textViewAddress = (TextView) findViewById(R.id.tvUserAccountAddress);
        textViewAge = (TextView) findViewById(R.id.tvUserAccountAge);


        //set the title of activity
        this.setTitle("User Account Detail");

        Profile profile = Profile.getCurrentProfile();
        //get ID from facebook
        ID = profile.getId().toString();


        //method to convert the JSONString of user and extract the correct user details to be used
        getUserDetail();

        //userAccountDetailValues = new ArrayList<>();
        //userAccountDetailValues = this.matchID(userAccountDetailValues, ID);

        textViewFirstName.setText(userAccountDetailValues.get(1));
        textViewLastName.setText(userAccountDetailValues.get(2));
        textViewAge.setText(userAccountDetailValues.get(3));
        textViewAddress.setText(userAccountDetailValues.get(4));
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

//    private ArrayList<String> matchID(Object[] userAccountDetailValues, String ID){
//
//        for(int i = 0; i < userAccountDetailValues.length; i++){
//            ArrayList<String> tempArrayList = (ArrayList<String>) userAccountDetailValues[i];
//            if(ID == tempArrayList.get(0)){
//                return tempArrayList;
//            }
//        }
//        return null;
//    }

    public void getUserDetail(){
        Intent intent = getIntent();
        String JSONString = intent.getStringExtra("JSONStringForUserDetail");
        ConvertJSON convertJSON = new ConvertJSON(JSONString,JSONARRAY);

        //Value in TreeMap is the following order
        //id, firstName, lastName, age, address, permission
        userAccountDetailValues = convertJSON.getTreeMap().get(ID);

//        //Convert treemap into object array to pass into adapter
//        Collection<ArrayList<String>> collection = userAccountDetailTreeMap.values();
//        //userAccountDetailValues = collection.toArray();

    }

    public void onButtonForSave(View view) {
        //this arraylist is for saving the value that need to write to database
        ArrayList<String> dataToWrite = new ArrayList<>();
        dataToWrite.add(ID);
        dataToWrite.add(editTextFirstName.getText().toString());
        dataToWrite.add(editTextLastName.getText().toString());
        dataToWrite.add(editTextAge.getText().toString());
        dataToWrite.add(editTextAddress.getText().toString());
        dataToWrite.add(permission);

        WriteToDatabase writeToDatabase = new WriteToDatabase(columnName,dataToWrite,url,UserAccountDetail.this);
        writeToDatabase.write();

        //start new activity after writing to database
        Intent intent = new Intent(UserAccountDetail.this, testClass.class);
        startActivity(intent);
    }

    // when click the edit button, then all textView, placed right side, and edit button
    // are unvisible then editTexts and save button show up to user.
    public void onButtonForEdit(View view) {

        // textViews and edit button become unvisible.
        textViewFirstName.setVisibility(View.GONE);
        textViewLastName.setVisibility(View.GONE);
        textViewAddress.setVisibility(View.GONE);
        textViewAge.setVisibility(View.GONE);
        buttonEdit.setVisibility(View.GONE);

        // editTexts and save button show up to user.
        editTextFirstName.setVisibility(View.VISIBLE);
        editTextLastName.setVisibility(View.VISIBLE);
        editTextAddress.setVisibility(View.VISIBLE);
        editTextAge.setVisibility(View.VISIBLE);
        buttonSave.setVisibility(View.VISIBLE);
    }
}
