package com.foodorderingapp;

/**
 * Created by User on 9/23/2016.
 */

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class RestaurantDisplayTest {
//    @Rule
//    public ActivityTestRule<RestaurantDisplay> getButton =
//            new ActivityTestRule(ButtonGoToRestaurantSelection.class,true,true);

    @Rule
        public ActivityTestRule<RestaurantDisplay> startClass = new ActivityTestRule<RestaurantDisplay>(RestaurantDisplay.class,true,true);

    String JsonString = "{\"Restaurant\":" +
            "[" +
            "{\"name\":\"Burger King\"," +
            "\"ID\":\"001\"}," +
            "{\"name\":\"Subway\"," +
            "\"ID\":\"002\"}," +
            "]" +
            "}";

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

    }
}
