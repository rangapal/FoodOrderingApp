package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import static com.foodorderingapp.GlobalVariable.allMenuItemsQuantity;

/*
    This class display all menu items of the selected restaurant
 */
public class MenuDisplay extends NavigationDrawerUser {
    ListView listViewMenu;
    TreeMap<String, ArrayList<String>> menuTreeMap;
    Button buttonOrderSummary;
    private final String JSONARRAYMenu = "Menu";
    private ArrayList<String> menuDetailArray;
    private String menuName;
    private Object[] menuValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_view);

        navigation_drawer();//set the toolbar and navigation drawer
        menuTreeMap = new TreeMap<>();
        listViewMenu = (ListView) findViewById(R.id.listViewMenuDisplay);
        buttonOrderSummary = (Button)findViewById(R.id.buttonMenuOrderSummary);
        Intent intent = getIntent();

        //get the restaurant ID so we can populate the menu for the restaurant
        String restaurantName = intent.getStringExtra("restaurantID");
        this.setTitle(restaurantName +" Menu");

        //get the jsonString so we can convert it to treeMap
        String JSONStringMenu = intent.getStringExtra("JSONStringMenu");
        ConvertJSON convertJSON = new ConvertJSON(JSONStringMenu, JSONARRAYMenu);

        //Value in TreeMap is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
        menuTreeMap = convertJSON.getTreeMap();

        //Convert treemap into object array to pass into adapter
        Collection<ArrayList<String>> collection = menuTreeMap.values();
        menuValues = collection.toArray();

        //putting the menu name and quantity to the allMenuItemsQuantity variable
        for(int i =0; i < menuValues.length;i++)
        {
            menuDetailArray = (ArrayList<String>) menuValues[i];
            menuName = menuDetailArray.get(0); // getting the menu name
            //check if the menu already exist in the allMenuItemsQuantity
            //if it does not exsit, set it to zero
            if(!(allMenuItemsQuantity.containsKey(menuName)))
            allMenuItemsQuantity.put(menuName,"0");
        }

        //set adapter for listview
        listViewMenu.setAdapter(new MenuDisplayAdapter(MenuDisplay.this, menuValues));

        //when an item is click from the listview, it goes to menu detail activity
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                menuDetailArray = (ArrayList<String>) menuValues[position];
                Intent menuDetail = new Intent(MenuDisplay.this,MenuDetail.class);
                //passing the details of the menu to MenuDetail class
                menuDetail.putExtra("ClickMenu", menuDetailArray);
                MenuDisplay.this.startActivity(menuDetail);
            }
        });
    }

    //when viewOrderSummary button is click, it goes to TotalPrice class
    public void onClick(View view) {
        if(view == buttonOrderSummary){
            Intent totalPriceDisplay = new Intent(MenuDisplay.this, TotalPriceDisplay.class);

            //ArrayList that contains all menu details that is selected
            ArrayList<ArrayList<String>> selectedMenu = new ArrayList<>();

            //loop to get all menu detail
            for(int i =0; i < menuValues.length;i++) {
                menuDetailArray = (ArrayList<String>) menuValues[i]; //get the each menu detail
                menuName = menuDetailArray.get(0); // get menu Name

                // get integer value of the menu
                int quantity = Integer.parseInt(allMenuItemsQuantity.get(menuName));

                //add menu quantity only when the value is greater than 0
                if(quantity > 0){
                    // if quantity variable is not in the arraylist, add it to arraylist
                    // else set the quantity with new value
                    if(menuDetailArray.size() == 6)
                        menuDetailArray.add(allMenuItemsQuantity.get(menuName));
                    else
                        menuDetailArray.set(6, allMenuItemsQuantity.get(menuName));
                    selectedMenu.add(menuDetailArray);
                }
            }
            //check for when user did not choose any menu item
            if(selectedMenu.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please choose a menu item before proceed", Toast.LENGTH_SHORT).show();
            }else{
                MenuDisplay.this.startActivity(totalPriceDisplay);
            }
            //set menu quantity to global variable for easy usage
            GlobalVariable.userSelectedMenuItemQuantity = selectedMenu;
        }
    }

    //update the display when menu quantity is changed
    @Override
    public void onRestart(){
        super.onRestart();
        listViewMenu.setAdapter(new MenuDisplayAdapter(MenuDisplay.this, menuValues));
    }
}
