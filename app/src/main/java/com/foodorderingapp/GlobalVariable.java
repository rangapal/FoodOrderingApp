package com.foodorderingapp;

import java.util.TreeMap;

/**
 * Created by User on 9/23/2016.
 */
public class GlobalVariable {
    public static TreeMap<String,String> menuItemQuantity;

    public GlobalVariable(){
        menuItemQuantity = new TreeMap<>();
    }
}
