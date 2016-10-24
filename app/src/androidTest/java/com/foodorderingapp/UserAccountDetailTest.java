package com.foodorderingapp;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by User on 9/23/2016.
 */
@RunWith(AndroidJUnit4.class)
public class UserAccountDetailTest {

    @Rule
    public ActivityTestRule<Login> login = new ActivityTestRule<>(Login.class);

    @Rule
    public IntentsTestRule<Login> loginIntent = new IntentsTestRule<>(Login.class);

    @Before
    public void goToUserAccountDetails(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("User Account")).perform(click());
    }

    /*
    Test whether the user information is display properly from database
    Expected Result:
    FirstName: John     LastName: Smith     Age: 20     Adress: City Centre, Auckland
     */
    @Test
    public void testDisplayUserInformation(){
        //check the textview existed and show on screen
        onView(withId(R.id.tvUserAccountFirstName)).check(matches(isDisplayed()));
        onView(withId(R.id.tvUserAccountLastName)).check(matches(isDisplayed()));
        onView(withId(R.id.tvUserAccountAge)).check(matches(isDisplayed()));
        onView(withId(R.id.tvUserAccountAddress)).check(matches(isDisplayed()));

        //check the content of the textview
        onView(withId(R.id.tvUserAccountFirstName)).check(matches(withText("John")));
        onView(withId(R.id.tvUserAccountLastName)).check(matches(withText("Smith")));
        onView(withId(R.id.tvUserAccountAge)).check(matches(withText("20")));
        onView(withId(R.id.tvUserAccountAddress)).check(matches(withText("City Centre, Auckland")));
    }

    /*
    Test pressing on the edit button
    Expected Result: editText is shown and allow user to input data
     */
    @Test
    public void testPressEditButton(){
        onView(withId(R.id.buttonEditUserAccountDetail)).perform(click());

        //check editText existed and show on screen
        onView(withId(R.id.etUserAccountDetailFirstName)).check(matches(isDisplayed()));
        onView(withId(R.id.etUserAccountDetailLastName)).check(matches(isDisplayed()));
        onView(withId(R.id.etUserAccountDetailAge)).check(matches(isDisplayed()));
        onView(withId(R.id.etUserAccountDetailAddress)).check(matches(isDisplayed()));

        //verify the type text whether it is displaying properly
        onView(withId(R.id.etUserAccountDetailFirstName)).perform(clearText()).perform(typeText("first")).check(matches(withText("first")));
        onView(withId(R.id.etUserAccountDetailLastName)).perform(clearText()).perform(typeText("last")).check(matches(withText("last")));
        onView(withId(R.id.etUserAccountDetailAge)).perform(clearText()).perform(typeText("12")).check(matches(withText("12")));
        onView(withId(R.id.etUserAccountDetailAddress)).perform(clearText()).perform(typeText("address")).check(matches(withText("address")));
    }

    /*
    Test pressing on the Logout button
    Expected Result: user is log out and go to login page
     */
    @Test
    public void testPressLogOutButton(){
        onView(withId(R.id.buttonLogOutUserAccountDetail)).perform(click());
        intended(hasComponent(Login.class.getName()));
    }
}
