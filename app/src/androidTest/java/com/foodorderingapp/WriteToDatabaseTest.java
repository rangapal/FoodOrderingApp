package com.foodorderingapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.TreeMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by User on 10/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class WriteToDatabaseTest {
    @Rule
    public ActivityTestRule<CustomerHomePage> customerHomepage = new ActivityTestRule<>(CustomerHomePage.class);

    ArrayList<String> dataToWrite;

    /*
    hard code are dataToWrite and columnName
    Expect result: ArrayList contains those value
     */
    @Test
    public void writeUserDataToDatabase(){
        ArrayList<String> columnName = new ArrayList<>();
        columnName.add("id");
        columnName.add("firstName");
        columnName.add("lastName");
        columnName.add("age");
        columnName.add("address");
        columnName.add("permission");

        //write Database
        dataToWrite = new ArrayList<>();
        dataToWrite.add("123");
        dataToWrite.add("John");
        dataToWrite.add("Smith");
        dataToWrite.add("12");
        dataToWrite.add("Auckland, New zealand");
        dataToWrite.add("user");

        String urlWriteUserToDB = "http://aaacars.co.nz/writeToUserData.php";
        WriteToDatabase writeToDatabase = new WriteToDatabase(columnName,dataToWrite,urlWriteUserToDB,customerHomepage.getActivity());
        writeToDatabase.write();
    }

    //Retrieve user data from database
    @Test
    @UiThreadTest
    public void checkUserDataOnDB(){
        writeUserDataToDatabase();
        String URLAccount = "http://aaacars.co.nz/getUser.php";
        ConnectAndRetrieveDB connectDBAccount = new ConnectAndRetrieveDB(
                customerHomepage.getActivity(),URLAccount,new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                convertAndGetUserData(s);// check to confirm the written string
            }
        },"none");
        connectDBAccount.execute();
    }

    //convert the JSONString and check for value
    public void convertAndGetUserData(String s){
        ConvertJSON convertJSON = new ConvertJSON(s,"User");
        TreeMap<String,ArrayList<String>> treeMap = convertJSON.getTreeMap();

        ArrayList<String> checkData = treeMap.get(dataToWrite.get(0));

        for(int i = 0; i < checkData.size(); i++)
            assertEquals(dataToWrite.get(i),checkData.get(i));
    }

}
