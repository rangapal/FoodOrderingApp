package com.foodorderingapp;

/**
 * Created by User on 9/23/2016.
 */

import android.widget.ListView;

import org.junit.Test;


import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class RestaurantDisplayTest {
//    @Rule
//    public ActivityTestRule<RestaurantDisplay> restaurantDisplay =
//            new ActivityTestRule(ButtonGoToRestaurantSelection.class);

//    @Rule
//    public ActivityTestRule<RestaurantDisplay> getButton =
//            new ActivityTestRule(RestaurantDisplay.class);



//    @Test
//    public void validateRestaurantDisplay() {
//        onView(withId(R.id.buttonGet)).perform(click());
//        intended(hasComponent(activityTestRule.toString()));
//    }

    //@Test
    public void checkQuantity(){
        //onView(withId(R.id.buttonGet)).perform(click());

        //getButton.getActivity();
//        onView(withId(R.id.buttonMinusMenuDetail))
//                .check(ViewAssertions.matches(ViewMatchers.withText(R.id.editTextMenuDetailQuantity)))
//                .perform(ViewActions.click());
    }
    @Test
    public void checkItemInRestaurantDisplay() {
        //onView(R.id.detail
    }
}
