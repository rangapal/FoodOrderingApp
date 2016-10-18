package com.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

public class testClass extends NavigationDrawerUser implements View.OnClickListener{
    Button buttonOrder;
    Button buttonUser;
    Button buttonLogOut;
    Button buttonGoToUserDetail;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        buttonLogOut = (Button)findViewById(R.id.buttonLogOutTest);
        buttonOrder = (Button)findViewById(R.id.buttonOrder);
        buttonUser = (Button)findViewById(R.id.buttonUser);
        buttonGoToUserDetail = (Button) findViewById(R.id.buttonGoTOUserDetail);

        buttonLogOut.setOnClickListener(this);
        buttonUser.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);
        buttonGoToUserDetail.setOnClickListener(this);

        navigation_drawer();
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
            Intent intent = new Intent(testClass.this,FirstTimeUser.class);

            startActivity(intent);

        }
        else if(v == buttonGoToUserDetail){
            ConnectAndRetrieveDB connectAndRetrieveDB = new ConnectAndRetrieveDB(
                    testClass.this, "http://aaacars.co.nz/getUser.php", getUserDetail(), "none");
            connectAndRetrieveDB.execute();

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

    private AsyncResponse getUserDetail() {
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent = new Intent(testClass.this, UserAccountDetail.class);
                intent.putExtra("JSONStringForUserDetail", s);
                startActivity(intent);
            }
        };

        return asyncResponse;
    }
}
