package com.vivek.amz.locker.model;

import java.util.List;
import java.util.UUID;

public class Pack {

    private final String id;
    private final String orderId;
    private final List<Item> items;
    private double packageSize;

    public Pack(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        pack();
    }

    private void pack() {
        packageSize = Math.random() * 10;
    }

    public String getId() {
        return id;
    }

    public double getPackageSize() {
        return packageSize;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }
}
