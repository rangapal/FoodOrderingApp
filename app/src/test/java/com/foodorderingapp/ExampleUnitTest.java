package com.foodorderingapp;


import android.util.Log;

import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.TreeMap;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    //hardcode are JsonString & JSONArray String
    //Expect result: treeMap containing those values
    //@Test
    public void convertJSONTest(){
        String JsonString = "{\"Restaurant\":" +
                                "[" +
                                    "{\"name\":\"Burger King\"," +
                                    //"\"phoneNumber\":\"95315841\"," +
                                    "\"ID\":\"001\"}," +
                                    "{\"name\":\"Subway\"," +
                                    //"\"phoneNumber\":\"98132566\"," +
                                    "\"ID\":\"002\"}," +
                                "]" +
                            "}";

        //actual method with coding
        ConvertJSON convertJSON = new ConvertJSON(JsonString, "Restaurant");

        TreeMap<String, ArrayList<String>> treeMap = convertJSON.getTreeMap();

        //hardcode arrayList for test
        TreeMap<String,ArrayList<String>> testTreeMap = new TreeMap();
        ArrayList<String> arrayList = new ArrayList<>(3);
        arrayList.add("Burger King");
        arrayList.add("001");

        assertEquals("Burger King",treeMap.firstKey());
        assertEquals(arrayList,treeMap.values().toArray()[0]);

        ArrayList<String> newArrayList = new ArrayList<>(3);
        newArrayList.add("Subway");
        newArrayList.add("002");

        assertEquals(newArrayList,treeMap.values().toArray()[1]);
        assertEquals("Subway", treeMap.lastKey());
    }

    @Test
    public void getTotalPrice() throws Exception {

        String[] price = {"10","20","30"};
        String[] quantity = {"1","3","5"};

        TotalPriceDisplay tp = new TotalPriceDisplay();
        float total = tp.getTotalPrice(price,quantity);

        float totalPriceCalculation = 10*1+20*3+30*5;

        assertEquals(total, totalPriceCalculation, 0.0);
    }




}