package com.vivek.online.shopping.model.product;

public class Product {

    private String productId;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private int availableQuantity;
    private String seller;

    int getAvailableQuantity() {
        return availableQuantity;
    }

    void updatePrice(double newPrice) {
        price = newPrice;
    }

}
