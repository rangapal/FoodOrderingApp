package com.foodorderingapp;

/**
 * Created by User on 9/22/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    private CallbackManager callbackManager = null;
    private AccessTokenTracker mtracker = null;
    private ProfileTracker mprofileTracker = null;
    private LoginButton loginButton;

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            goToSuitableActivity(profile);
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException e) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        mtracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.v("AccessTokenTrakcer", "oldAccessToken=" + oldAccessToken + "||" + "CurrentAccessToken" + currentAccessToken);
            }
        };

        mprofileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                Log.v("Session Tracker", "oldProfile=" + oldProfile + "||" + "currentProfile" + currentProfile);
                goToSuitableActivity(currentProfile);
            }
        };

        mtracker.startTracking();
        mprofileTracker.startTracking();
    }

    private void goToSuitableActivity(Profile profile){
        if(profile!=null){
            initiateActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");

        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager,callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onStop() {
        super.onStop();
        mtracker.stopTracking();
        mprofileTracker.stopTracking();
    }

    public boolean isLoggedIn(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken!=null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isLoggedIn()){
            loginButton.setVisibility(View.INVISIBLE);
            Profile profile = Profile.getCurrentProfile();
            goToSuitableActivity(profile);
        }

    }

    private void initiateActivity(){
        //connect to database and execute the intent
        ConnectAndRetrieveDB connectDB = new ConnectAndRetrieveDB(
                getContext(),"http://aaacars.co.nz/getUser.php",checkUser(),"none");
        connectDB.execute();
    }

    //this method check for the user permission and intent activity accordingly
    private AsyncResponse checkUser(){
        AsyncResponse asyncResponse = new AsyncResponse() {
            @Override
            public void onTaskComplete(Object object) {
                String s = (String) object;
                Intent intent;

                //go to user detail page for new user to input their details
                if(isNewUser(s)){
                    intent = new Intent(getContext(), FirstTimeUser.class);
                }else{
                    //go to RestaurantOwnerHomePage page for admin
                    if(isAdmin(s)){
                        intent = new Intent(getContext(), RestaurantOwnerHomePage.class);
                    }else{ // go to restaurant selection page for current user
                        intent = new Intent(getContext(), testClass.class);
                    }
                }
                startActivity(intent);
            }
        };
        return asyncResponse;
    }

    //this method is use to check for new user to the app
    private boolean isNewUser(String JSONStringUser){
        Profile profile = Profile.getCurrentProfile();
        ConvertJSON convertJSON = new ConvertJSON(JSONStringUser,"User");

        if(convertJSON.getTreeMap().containsKey(profile.getId())){
            return false;
        }else{
            return true;
        }
    }

    //this method is use to check whether user permission is admin or user
    private boolean isAdmin(String JSONString){
        Profile profile = Profile.getCurrentProfile();
        ConvertJSON convertJSON = new ConvertJSON(JSONString,"User");

        ArrayList<String> userDetails = convertJSON.getTreeMap().get(profile.getId());

        //5 is the index for permission
        if(userDetails.get(5).equals("admin")){
            return true;
        }else{
            return false;
        }
    }
}
