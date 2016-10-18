package com.foodorderingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * Created by User on 9/23/2016.
 */
public class CustomerHomePage extends NavigationDrawerUser{
    TextView textViewWelcome;
    private final String navigationdrawerInfo = "navigationdrawerInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_homepage);

        //set the toolbar and navigation drawer
        navigation_drawer();
        showNavigationDrawerInfo();

        textViewWelcome = (TextView) findViewById(R.id.textViewWelcomeCustomerHomepage);


    }

    //this method is used for display the information on how to delete the menu item
    //it should only display once
    private void showNavigationDrawerInfo(){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean pressAndHoldToDeleteShown = mPrefs.getBoolean(navigationdrawerInfo, false);

        if(!pressAndHoldToDeleteShown){
            new AlertDialog.Builder(this).setTitle("Information").setMessage("Click on top left icon to access more functionality").setPositiveButton(
                    "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(navigationdrawerInfo, true);
            editor.commit();
        }
    }

}
