package com.foodorderingapp;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CheckUser {

    //hardcode are JsonString & JSONArray String
    //Expect result: treeMap containing those values
    @Test
    public void checkIsNewUser(){
        //hardcode JSONString
        String JSONString = "{\"User\":" +
                "[{\"id\":\"13384825361642820\",\"firstName\":\"Khychhim\",\"lastName\":\"lim\"," +
                "\"age\":\"20\",\"address\":\"77 sturges Raod\",\"permission\":\"user\"}" +
                "]}\"";

        LoginFragment loginFragment = new LoginFragment();
        Boolean check = loginFragment.isNewUser(JSONString);
        //assertTrue(check);
        Boolean trueMethod = true;
        assertEquals(trueMethod, check);


    }





}