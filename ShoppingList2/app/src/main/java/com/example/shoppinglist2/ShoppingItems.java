package com.example.shoppinglist2;

/**
 * Created by jcocklin on 6/26/2017.
 */

public class ShoppingItems {

    private String name;
    private double price;
    private int quantity;

    public ShoppingItems(String n, double p, int q) {
        this.name=n;
        this.price=p;
        this.quantity=q;
    }

    public double getPrice() { return this.price; }

    public int getQuantity() {
        return this.quantity;
    }

    public String getName() {
        return this.name;
    }
}
