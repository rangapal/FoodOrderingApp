package com.foodorderingapp;

/**
 * Created by User on 9/23/2016.
 */

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
public class NewTest {
    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each testClass
    @Rule
    public IntentsTestRule activityTestRule =
            new IntentsTestRule<>(ButtonGoToRestaurantSelection.class);

    // Looks for an EditText with id = "R.id.etInput"
    // Types the text "Hello" into the EditText
    // Verifies the EditText has text "Hello"
    @Test
    public void validateEditText() {
        //Intents.init();
        onView(withId(R.id.buttonGet)).perform(click());
        intended(hasComponent(RestaurantDisplay.class.getName()));
        //Intents.release();
    }
}
