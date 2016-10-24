package com.foodorderingapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 9/23/2016.
 */
@RunWith(AndroidJUnit4.class)
public class RestaurantDisplayTest {

    @Rule
    public ActivityTestRule<NavigationDrawerUser> loginClass = new ActivityTestRule<>(NavigationDrawerUser.class,true,true);

    @Test
    public void checkIsNewUser(){
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));


        //assertEquals(falseStatment, check);
    }


    @Test
    @UiThreadTest
    public void checkIsAdmin(){

    }
}
