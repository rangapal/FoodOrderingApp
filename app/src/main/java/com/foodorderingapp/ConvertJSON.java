package com.foodorderingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by User on 9/18/2016.
 */
public class ConvertJSON {
    private TreeMap<String, ArrayList<String>> treeMap;

    public ConvertJSON(String JSONString, String JSONArray){
        treeMap = new TreeMap<>();
        convertToHashMap(JSONString, JSONArray);
    }

    public void convertToHashMap(String JSONString, String JSONArray){
        try {
            JSONObject jsonObjectRoot = new JSONObject(JSONString);
            JSONArray jsonArray = jsonObjectRoot.getJSONArray(JSONArray);

            //this is used for storing each column name
            ArrayList<String> valueTag = new ArrayList<>();

            //this is used for adding value of the column to hashmap
            ArrayList<String> eachColumnArray;

            //add each column name to valueTag
            Iterator<String> iterator = jsonArray.getJSONObject(0).keys();
            while(iterator.hasNext()){
                valueTag.add(iterator.next());
            }

            JSONObject jo;

            //loop to get number of item object in jsonArray
            for(int i = 0; i < jsonArray.length(); i++){

                eachColumnArray = new ArrayList<>();// initiate new ArrayList

                jo = jsonArray.getJSONObject(i); // get the i jsonObject

                //loop to get value of each name/value pair
                for(int j = 0; j < valueTag.size(); j++){
                    //get string value from name/value and add it to arraylist
                    eachColumnArray.add(jo.getString(valueTag.get(j)));
                }

                //add each restaurant ArrayList to HashMap
                    treeMap.put(eachColumnArray.get(0),eachColumnArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //method for getting hashmap
    public TreeMap<String,ArrayList<String>> getTreeMap()
    {
        return this.treeMap;
    }
}
