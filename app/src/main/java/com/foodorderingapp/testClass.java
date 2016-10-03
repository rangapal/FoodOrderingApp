package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

public class testClass extends AppCompatActivity implements View.OnClickListener{
    Button buttonOrder;
    Button buttonUser;
    Button buttonLogOut;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        buttonLogOut = (Button)findViewById(R.id.buttonLogOut);
        buttonOrder = (Button)findViewById(R.id.buttonOrder);
        buttonUser = (Button)findViewById(R.id.buttonUser);

        buttonLogOut.setOnClickListener(this);
        buttonUser.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);
//
//
//        button = (Button)findViewById(R.id.buttonGet);
//
//    }
//    public void onClick(View view) {
////        HomeFragment homeFragment = new HomeFragment();
////        Intent intent = new Intent(testClass.this, homeFragment.getClass());
////        testClass.this.startActivity(intent);
//
//        GetJSON getJSON = new GetJSON(testClass.this,"http://aaacars.co.nz/getRestaurant.php",RestaurantDisplay.class,null);
//
//        //String s = getJSON.getJsonString();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogOut){
            logout();

        }
        else if(v == buttonOrder){

            ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                    testClass.this,"http://aaacars.co.nz/getRestaurant.php",initiateRestaurantDisplay(),"none");
            connectDB.execute();
        }
        else if(v == buttonUser){
            Intent intent = new Intent(testClass.this,UserDetails.class);
            startActivity(intent);

        }
    }

    private void logout(){
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(testClass.this,Login.class);
        startActivity(intent);
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.mainContainer,new LoginFragment());
//        fragmentTransaction.commit();
    }

    private AsyncResponse initiateRestaurantDisplay() {
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(testClass.this, RestaurantDisplay.class);
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
