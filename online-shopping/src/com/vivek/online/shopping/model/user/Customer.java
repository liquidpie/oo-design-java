package com.vivek.online.shopping.model.user;

import com.vivek.online.shopping.model.Item;
import com.vivek.online.shopping.model.order.Order;
import com.vivek.online.shopping.model.ShoppingCart;

public class Customer {

    private Account account;
    private ShoppingCart cart;
    private Order order;

    ShoppingCart getCart() {
        return cart;
    }

    void addItemToCart(Item item) { }

    void removeItemFromCart(Item item) { }

}
