package com.foodorderingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * test for first time user
 */
@RunWith(AndroidJUnit4.class)
public class FirstTimeUserTest {

    //For this to work, the user must be a new user login for first time
    //In our case, we delete our user data from database
    @Rule
    public ActivityTestRule<Login> firstTimeUser = new ActivityTestRule<>(Login.class);

    /*
    Test whether the text view is display properly
    Expected Result: empty string on each of the item
    FirstName:      LastName:      Age:      Adress:
     */
    @Test
    public void testViewDisplay(){
        //check the editText existed and show on screen
        onView(withId(R.id.etFirstTimeUserFirstName)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstTimeUserLastName)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstTimeUserAge)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstTimeUserAddress)).check(matches(isDisplayed()));

        //check the content of the editText which should be empty
        onView(withId(R.id.etFirstTimeUserFirstName)).check(matches(withText("")));
        onView(withId(R.id.etFirstTimeUserLastName)).check(matches(withText("")));
        onView(withId(R.id.etFirstTimeUserAge)).check(matches(withText("")));
        onView(withId(R.id.etFirstTimeUserAddress)).check(matches(withText("")));
    }

    /*
    Test entering text on the editview
    Expected Result: editText value is the same as entered
     */
    @Test
    public void testUserInput(){
        //verify the type text whether it is showing properly
        onView(withId(R.id.etFirstTimeUserFirstName)).perform(clearText()).perform(typeText("first")).check(matches(withText("first")));
        onView(withId(R.id.etFirstTimeUserLastName)).perform(clearText()).perform(typeText("last")).check(matches(withText("last")));
        onView(withId(R.id.etFirstTimeUserAge)).perform(clearText()).perform(typeText("12")).check(matches(withText("12")));
        onView(withId(R.id.etFirstTimeUserAddress)).perform(clearText()).perform(typeText("address")).check(matches(withText("address")));
    }
}
