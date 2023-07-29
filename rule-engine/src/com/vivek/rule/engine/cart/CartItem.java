package com.vivek.rule.engine.cart;

import java.util.HashSet;
import java.util.Set;

public class CartItem {

    private final String productId;
    private final Set<ProductCategory> categories;
    private final int quantity;

    public CartItem(String productId, Set<ProductCategory> categories, int quantity) {
        this.productId = productId;
        this.categories = categories;
        this.quantity = quantity;
    }

    public CartItem(String productId, ProductCategory category, int quantity) {
        this.productId = productId;
        this.categories = new HashSet<>();
        this.categories.add(category);
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public int getQuantity() {
        return quantity;
    }

}
