package com.foodorderingapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

public class BaseNavigationDrawer extends AppCompatActivity{
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    private String fragmentArray[] = {"Order","User Account"};
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    public void navigation_drawer(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        listView = (ListView) findViewById(R.id.left_drawer);
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,fragmentArray);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                                BaseNavigationDrawer.this,"http://aaacars.co.nz/getRestaurant.php",initiateRestaurantDisplay(),"none");
                        connectDB.execute();
                        break;
                    case 1:
                        //fragment = new Fragment_two();
                        break;
                    default:
                        //fragment = new Fragment_one();
                        break;
                }
                //FragmentManager fragmentManager = getSupportFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.relativeLayout,fragment).commit();
            }
        });
    }

    private AsyncResponse initiateRestaurantDisplay() {
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(BaseNavigationDrawer.this, RestaurantDisplay.class);
                intent.putExtra("JSONString", s);
//                if(post != null){
//                    intent.putExtra("restaurantID",post);
//                }
                startActivity(intent);
            }
        };

        return asyncResponse;
    }
}
