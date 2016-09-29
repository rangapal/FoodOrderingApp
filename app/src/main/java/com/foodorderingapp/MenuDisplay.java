package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class MenuDisplay extends AppCompatActivity {
    ListView listViewMenu;
    Button buttonOrderSummary;
    private final String JSONARRAY = "Menu";
    private ArrayList<String> menuDetailArray;
    TreeMap<String, ArrayList<String>> menuTreeMap;
    private String menuName;
    private Object[] menuValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_view);
        menuTreeMap = new TreeMap<>();
        listViewMenu = (ListView) findViewById(R.id.listViewMenuDisplay);

        Intent intent = getIntent();

        buttonOrderSummary = (Button)findViewById(R.id.buttonMenuOrderSummary);

        //get the restaurant ID so we can populate the menu for the restaurant
        String restaurantName = intent.getStringExtra("restaurantID");
        this.setTitle(restaurantName +" Menu");

        //get the jsonString so we can convert it to treeMap
        String JSONString = intent.getStringExtra("JSONString");
        ConvertJSON convertJSON = new ConvertJSON(JSONString,JSONARRAY);

        //Value in TreeMap is the following order
        //menuName, menuPrice, menuDescription, menuImage, restaurantName, restaurant ID
        /*
         This treemap is use to check restaurant name that user select
         and get all the details for the menu and put it in a the menuTreeMap
          */
        menuTreeMap = convertJSON.getTreeMap();

        //Convert treemap into object array to pass into adapter
        Collection<ArrayList<String>> collection = menuTreeMap.values();
        menuValues = collection.toArray();

        //putting the menu name and quantity to the menuItemQuantity variable
        for(int i =0; i < menuValues.length;i++)
        {
            menuDetailArray = (ArrayList<String>) menuValues[i];
            menuName = menuDetailArray.get(0); // getting the menu name
            //check if the menu exist in the menuItemQuantity yet
            //if it does not exsit, set it to zero
            if(!(GlobalVariable.menuItemQuantity.containsKey(menuName)))
            GlobalVariable.menuItemQuantity.put(menuName,"0");
        }

        //set adapter for lsitview
        listViewMenu.setAdapter(new MenuDisplayAdapter(MenuDisplay.this, menuValues));

        //when an item is click from the listview, it goes to menu detail activity
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                menuDetailArray = (ArrayList<String>) menuValues[position];
                Intent menuDetail = new Intent(MenuDisplay.this,MenuDetail.class);

                //passing the details of the menu to menu detail activity
                menuDetail.putExtra("ClickMenu", menuDetailArray);
                MenuDisplay.this.startActivity(menuDetail);
            }
        });
    }

    //when viewOrderSummary button is click, it goes to totalprice activity
    public void onClick(View view) {
        Intent totalPriceDisplay = new Intent(MenuDisplay.this, TotalPriceDisplay.class);

        //ArrayList that contains all menu details that is select
        ArrayList<ArrayList<String>> selectedMenu = new ArrayList<>();

        //loop to get all menu detail
        for(int i =0; i < menuValues.length;i++) {
            menuDetailArray = (ArrayList<String>) menuValues[i]; //get the each menu detail
            menuName = menuDetailArray.get(0); // get menu Name

            // get integer value of the menu
            int quantity = Integer.parseInt(GlobalVariable.menuItemQuantity.get(menuName));

            //add menu quantity only when the value is greater than 0
            if(quantity > 0){
                menuDetailArray.add(GlobalVariable.menuItemQuantity.get(menuName));
                selectedMenu.add(menuDetailArray);
            }
        }

        totalPriceDisplay.putExtra("SelectedMenu", selectedMenu);
        MenuDisplay.this.startActivity(totalPriceDisplay);
    }

    //refresh the listview when menu quantity is changed
    @Override
    public void onRestart(){
        super.onRestart();
        listViewMenu.setAdapter(new MenuDisplayAdapter(MenuDisplay.this, menuValues));
    }
}
