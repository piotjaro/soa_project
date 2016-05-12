package com.model;

import java.io.Serializable;

/**
 * Created by piotrek on 12.05.16.
 */
public class Ingredient implements Serializable{
    private int id;
    private String name;
    private double quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
