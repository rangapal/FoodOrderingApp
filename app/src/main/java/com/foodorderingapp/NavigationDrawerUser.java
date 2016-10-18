package com.foodorderingapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by User on 10/7/2016.
 */

public class NavigationDrawerUser extends AppCompatActivity{
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private String navigationDrawerItemsName[] = {"Order","User Account"};
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String URL = "http://aaacars.co.nz/getRestaurant.php";
    private String postRequestString = "none";

    public void navigation_drawer(){

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set the icon of navigation drawer
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        //open the navigation drawer when click on icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //set the list view
        listView = (ListView) findViewById(R.id.left_drawer);
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1, navigationDrawerItemsName);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                                NavigationDrawerUser.this,URL,initiateRestaurantDisplay(),postRequestString);
                        connectDB.execute();
                        break;
                    case 1:
                        //fragment = new Fragment_two();
                        break;
                }
            }
        });
    }

    //method to get restaurant details from database and go to restaurant display page
    private AsyncResponse initiateRestaurantDisplay() {
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(NavigationDrawerUser.this, RestaurantDisplay.class);
                intent.putExtra("JSONString", s);
                startActivity(intent);
            }
        };

        return asyncResponse;
    }
}
