package com.vivek.rule.engine.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItemToCart(String productId, ProductCategory category, int quantity) {
        items.add(new CartItem(productId, category, quantity));
    }

}
