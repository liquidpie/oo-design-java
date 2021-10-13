package com.vivek.online.shopping.model;

import java.util.List;

public class ShoppingCart {

    private List<Item> items;

    void addItem(Item item) { }

    void removeItem(Item item) { }

    void updateItemQuantity(Item item, int quantity) { }

    public List<Item> getItems() {
        return items;
    }

    void checkout() { }

}
