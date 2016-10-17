package com.foodorderingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 10/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class TotalPriceDisplayTest extends ActivityTestRule<TotalPriceDisplay>{

    public TotalPriceDisplayTest() {
        super(TotalPriceDisplay.class);
    }

    @Test
    public void setUp(){

    }
}
