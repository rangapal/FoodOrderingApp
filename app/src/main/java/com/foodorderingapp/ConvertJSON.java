package com.foodorderingapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class convert the JSONString into a treemap so data can be accessed easily
 */
public class ConvertJSON {
    private TreeMap<String, ArrayList<String>> treeMap;

    public ConvertJSON(String JSONString, String JSONArray){
        treeMap = new TreeMap<>();
        convertToTreeMap(JSONString, JSONArray);
    }

    public void convertToTreeMap(String JSONString, String JSONArrayString){
        try {
            //parse JSON object
            JSONObject jsonObjectRoot = new JSONObject(JSONString);
            JSONArray jsonArrayObject = jsonObjectRoot.getJSONArray(JSONArrayString);

            //this is used for storing each column name from database
            ArrayList<String> valueTag = new ArrayList<>();

            //this is used for adding value of the column  from database to hashmap
            ArrayList<String> eachColumnArray;

            //add each column name from database to valueTag
            Iterator<String> iterator = jsonArrayObject.getJSONObject(0).keys();
            while(iterator.hasNext()){
                valueTag.add(iterator.next());
            }

            JSONObject jo;

            //loop to get each item object in jsonArray
            for(int i = 0; i < jsonArrayObject.length(); i++){

                //since we are adding this to treemap, we have to clear the arraylist for each loop
                eachColumnArray = new ArrayList<>();//

                //this JSONobject contains each row of the table from database
                jo = jsonArrayObject.getJSONObject(i);

                // go through each valueTag to get each column value from the jo
                // and add it to each ColumnArray
                for(int j = 0; j < valueTag.size(); j++){
                    eachColumnArray.add(jo.getString(valueTag.get(j)));
                }

                //add each row value to HashMap
                //key is the first column value
                //value is the row value
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
