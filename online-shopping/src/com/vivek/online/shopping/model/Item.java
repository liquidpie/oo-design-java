package com.vivek.online.shopping.model;

public class Item {

    private String productId;
    private int quantity;
    private double amount;

    void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

}
