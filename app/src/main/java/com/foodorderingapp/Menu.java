package com.foodorderingapp;

import java.io.Serializable;

/**
 * Created by JeffChoi on 23/09/2016.
 * This is like the container for the intent of TotalPriceDetail
 */

public class Menu implements Serializable {
    private String name;
    private String price;
    private String quantity;
    private String description;
    private String imageId;

    public Menu() {
    }

    public Menu(String name, String price, String quantity, String description, String imageId){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageId = imageId;
    }

    public Menu(String name, String price, String quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
