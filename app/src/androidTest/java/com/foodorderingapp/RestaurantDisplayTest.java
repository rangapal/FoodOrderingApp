package com.foodorderingapp;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


/**
 * Created by User on 9/23/2016.
 */
@RunWith(AndroidJUnit4.class)
public class RestaurantDisplayTest {

//    @Rule
//    public IntentsTestRule<Login> loginIntent = new IntentsTestRule<>(Login.class);

    @Rule
    public ActivityTestRule<Login> loginTest = new ActivityTestRule<>(Login.class);

    /*
    Test: Opening and Closing Navigation Drawer
    Expected Result: Drawer should open when click and close when click again
     */
    @Test
    public void testOpenAndCloseDrawer(){
        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));//Drawer should be close to start.

        //open the drawer and check for confirmation
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isOpen()));

        //close the drawer and check for confirmation
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close()).check(matches(isClosed()));
    }

    /*
    Test: Whether the navigation drawer item names is shown correctly
    Expected Result: Position 0 is Home, Position 1 is Order, Position 2 is User Account
     */
    @Test
    public void checkDrawerItems(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        //check position 0 in listView
        onData(anything())
                .inAdapterView(withId(R.id.left_drawer))
                .atPosition(0)
                .check(matches(withText("Home")));
        //check item at position 1 in listView
        onData(anything())
                .inAdapterView(withId(R.id.left_drawer))
                .atPosition(1)
                .check(matches(withText("Order")));
        //check item at position 2 in listView
        onData(anything())
                .inAdapterView(withId(R.id.left_drawer))
                .atPosition(2)
                .check(matches(withText("User Account")));
    }

    /*
    Test whether the right activity is shown when click on Home in Navigation Drawer
    Expected Result: CustomerHomePage class is shown
     */
    @Test
    public void testPressHomeItem(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Home")).perform(click());
        intended(hasComponent(CustomerHomePage.class.getName()));
    }

    /*
    Test whether the right activity is shown when click on Order in Navigation Drawer
    Expected Result: RestaurantDisplay class is shown
     */
    @Test
    public void testPressOrderItem(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Order")).perform(click());
        intended(hasComponent(RestaurantDisplay.class.getName()));
    }

    /*
    Test whether the right activity is shown when click on User Account in Navigation Drawer
    Expected Result: UserAccountDetail class is shown
     */
    @Test
    public void testPressUserAccountItem(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("User Account")).perform(click());
        intended(hasComponent(UserAccountDetail.class.getName()));
    }


}
