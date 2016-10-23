package com.foodorderingapp;

/**
 * An abstract class used after the background process finishing running in the AsynTask
 */

public interface AsyncResponse {
    void onTaskComplete(Object object);
}
