package com.foodorderingapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class convert the JSONString into a TreeMap so data can be accessed easily
 */
public class ConvertJSON {
    private TreeMap<String, ArrayList<String>> treeMap;

    public ConvertJSON(String JSONString, String JSONArray){
        treeMap = new TreeMap<>();
        convertToTreeMap(JSONString, JSONArray);
    }

    public void convertToTreeMap(String JSONString, String JSONArrayString){
        try {
            //parsing JSON object and get JSON array of that object
            JSONObject jsonObjectRoot = new JSONObject(JSONString);
            JSONArray jsonArrayObject = jsonObjectRoot.getJSONArray(JSONArrayString);

            ArrayList<String> valueTag = new ArrayList<>();//this is used for storing each column name from database
            ArrayList<String> eachColumnArray;//this is used for adding value of the column  from database to hashmap

            //add each column name from database to valueTag
            //0 is the index of first item and .keys() is the method to get the item string name
            Iterator<String> iterator = jsonArrayObject.getJSONObject(0).keys();
            while(iterator.hasNext()){
                valueTag.add(iterator.next());
            }

            JSONObject jo; //a temporary variable for getting each jsonArrayObject

            //loop to get each item object in jsonArray and add it to treemap
            for(int i = 0; i < jsonArrayObject.length(); i++){
                //since we are adding this to treemap, we have to clear the arraylist for each loop
                eachColumnArray = new ArrayList<>();

                //this JSONobject contains ith row of the table from database
                jo = jsonArrayObject.getJSONObject(i);

                // go through each valueTag to get each column value from the jo
                // and add it to eachColumnArray. Therefore eachColumnArray would contains ith row values
                for(int j = 0; j < valueTag.size(); j++){
                    eachColumnArray.add(jo.getString(valueTag.get(j)));
                }
                //key is the first column value, value is the row value
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
