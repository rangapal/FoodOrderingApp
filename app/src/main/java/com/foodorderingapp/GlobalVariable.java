package com.foodorderingapp;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class is used for storing global variable
 */
public class GlobalVariable {
    //this treemap contains all the menu item quantity
    public static TreeMap<String,String> menuItemQuantity = new TreeMap<>();

    //this arraylist contains only the menu item has quantity bigger than zero
    public static ArrayList<ArrayList<String>> selectedMenuQuantity = new ArrayList<>();
}
