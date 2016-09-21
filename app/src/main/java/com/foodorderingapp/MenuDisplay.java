package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class MenuDisplay extends AppCompatActivity {
    ListView listViewMenu;
    private final String JSONARRAY = "Menu";
    TreeMap<String, ArrayList<String>> menuTreeMap;
    Object[] restaurantValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_grid_view);
        menuTreeMap = new TreeMap<>();

        listViewMenu = (ListView) findViewById(R.id.listViewMenuDisplay);

        Intent intent = getIntent();

        //get the restaurant name so we can populate the menu for the restaurant
        String restaurantName = intent.getStringExtra("restaurantName");

        //get the jsonString so we can convert it to treeMap
        String JSONString = intent.getStringExtra("JSONString");
        ConvertJSON convertJSON = new ConvertJSON(JSONString,JSONARRAY);
        //Value in TreeMap is the following order
        //restaurantName, menuName, menuPrice, menuDescription, menuImage
        /*
         This treemap is use to check restaurant name that user select
         and get all the details for the menu and put it in a the menuTreeMap
          */
        TreeMap<String, ArrayList<String>> tempTreeMap = convertJSON.getTreeMap();

//        for(int i = 0; i < tempTreeMap.size(); i++){
//            if(tempTreeMap.containsKey(restaurantName)){
//                ArrayList<String> menuDetail = new ArrayList<>();
//                tempTreeMap.get
//
//                menuTreeMap.put()
//            }
//        }

        menuTreeMap = tempTreeMap;

        //Convert treemap into object array to pass into adapter
        Collection<ArrayList<String>> collection = menuTreeMap.values();
        restaurantValues = collection.toArray();

        listViewMenu.setAdapter(new MenuDisplayAdapter(MenuDisplay.this, restaurantValues));

//        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ArrayList<String> restaurantDetailArray = (ArrayList<String>) restaurantValues[position];
//
//                Intent restaurantDetail = new Intent(MenuDisplay.this,MenuDetail.class);
//                restaurantDetail.putExtra("ClickRestaurant", restaurantDetailArray);
//                MenuDisplay.this.startActivity(restaurantDetail);
//
//            }
 //       });
    }
}
