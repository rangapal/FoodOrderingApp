package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class MenuDisplay extends AppCompatActivity {
    ListView listViewMenu;
    Button buttonOrderSummary;
    private final String JSONARRAY = "Menu";
    String quanity;
    TreeMap<String, ArrayList<String>> menuTreeMap;
    ArrayList<String> menuDetailArray;
    Object[] menuValues;
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_view);
        menuTreeMap = new TreeMap<>();
        quanity = "0";
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

        adapter = new MenuDisplayAdapter(MenuDisplay.this, menuValues);
        listViewMenu.setAdapter(adapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuDetailArray = (ArrayList<String>) menuValues[position];

                String menuName = menuDetailArray.get(0);
                GlobalVariable.menuItemQuantity.put(menuName,quanity);

                Intent menuDetail = new Intent(MenuDisplay.this,MenuDetail.class);
                menuDetail.putExtra("ClickMenu", menuDetailArray);

                //menuDetail.putExtra("Quantity",quanity);
                //startActivityForResult(menuDetail, 1);
                MenuDisplay.this.startActivity(menuDetail);

            }
        });
    }

    public void onClick(View view) {
        Intent totalPriceDisplay = new Intent(MenuDisplay.this, TotalPriceDisplay.class);

        //totalPriceDisplay.putExtra("")
        MenuDisplay.this.startActivity(totalPriceDisplay);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1) {
//            if(resultCode == Activity.RESULT_OK){
//                quanity = data.getStringExtra("Quantity");
//                adapter.notifyDataSetChanged();
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                //Write your code if there's no result
//            }
//        }
//    }
}
