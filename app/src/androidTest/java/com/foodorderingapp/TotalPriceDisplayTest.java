package com.foodorderingapp;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


/**
 * test class for Total price display
 */
@RunWith(AndroidJUnit4.class)
public class TotalPriceDisplayTest {

    @Rule
    public ActivityTestRule<Login> loginTest = new ActivityTestRule<>(Login.class);

    /*
        This is hard code : before going to totalPriceDisplay.Class, first click navigation bar to open it
        then click 'Order'. It then reach to RestaurantDisplay.Class then click 'Subway' to go to Restaurant detail.
        After that, click 'Go To MENU' to go to MenuDisplay.Class. There are 5 menu then click 'Roast Beef' menu to go to
        MenuDetail then click '+' button to add 2 quantities. It then click 'ADD To ORDER' to go back MenuDisplay.Class then click
        'Turkey and Ham' to go to MenuDetail then click '+' button to add 1 quantity. It then click 'ADD TO ORDER' to go back to
        MenuDisplay.Class then click 'VIEW ORDER SUMMARY' to approach to TotalPriceDisplay.Class
    */
    @Before
    public void goToTotalPriceDisplayPage(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Order")).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listViewRestaurant))
                .atPosition(1)
                .perform(click());
        onView(withId(R.id.buttonGoMenu)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listViewMenuDisplay))
                .atPosition(1)
                .perform(click());
        onView(withId(R.id.buttonPlusMenuDetail)).perform(click());
        onView(withId(R.id.buttonPlusMenuDetail)).perform(click());
        onView(withId(R.id.buttonMenuDetailOrder)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listViewMenuDisplay))
                .atPosition(3)
                .perform(click());
        onView(withId(R.id.buttonPlusMenuDetail)).perform(click());
        onView(withId(R.id.buttonMenuDetailOrder)).perform(click());
        onView(withId(R.id.buttonMenuOrderSummary)).perform(click());
    }

    /*
        Test : when the app reach to TotalPriceDisplay.Class, it check whether or not there are two menu,
        one is 'Roast Beef' and the other is 'Turkey and Ham' with name, quantity and price.
        Expectation value : the quantity of 'Roast Beef' should be '2'and that of 'Turkey and Ham' should be '1'.
     */
    //@Test
    public void checkViewDisplayProperly(){
        // check 'Roast Beef' with name, price and quantity
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(0)
                .onChildView(withId(R.id.textViewNameTotalPriceLayout)).check(matches(withText("Roast Beef")));
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(0)
                .onChildView(withId(R.id.textViewPriceValueTotalPriceLayout)).check(matches(withText("$ 20")));
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(0)
                .onChildView(withId(R.id.textViewQuantityValueTotalPriceLayout)).check(matches(withText("2")));

        //check 'Turkey and Ham' with name, price and quantity
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(1)
                .onChildView(withId(R.id.textViewNameTotalPriceLayout)).check(matches(withText("Turkey and Ham")));
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(1)
                .onChildView(withId(R.id.textViewPriceValueTotalPriceLayout)).check(matches(withText("$ 25")));
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(1)
                .onChildView(withId(R.id.textViewQuantityValueTotalPriceLayout)).check(matches(withText("1")));


    }
    /*
        Test : check whether or not the quantity is changed.
        Expectation value : The quantity of 'Roast Beef' is changed '2' to '1' and that of 'Turkey and Ham' is changed '1' to '4'
     */

    @Test
    public void testModifyQuantity(){
        // check whether or not decreasing quantity works
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(0)
                .perform(click());
        onView(withId(R.id.buttonMinusTotalPriceDetail)).perform(click());
        onView(withId(R.id.buttonTotalPriceDetailOrder)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(0)
                .onChildView(withId(R.id.textViewQuantityValueTotalPriceLayout)).check(matches(withText("1")));

        // check whether or not increasing quantity works
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(1)
                .perform(click());
        onView(withId(R.id.buttonPlusTotalPriceDetail)).perform(click());
        onView(withId(R.id.buttonPlusTotalPriceDetail)).perform(click());
        onView(withId(R.id.buttonPlusTotalPriceDetail)).perform(click());
        onView(withId(R.id.buttonTotalPriceDetailOrder)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.listViewTotalPrice))
                .atPosition(1)
                .onChildView(withId(R.id.textViewQuantityValueTotalPriceLayout)).check(matches(withText("4")));
    }
}
