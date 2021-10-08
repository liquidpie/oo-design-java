package com.vivek.amz.locker.model;

import java.util.List;

public class Order {

    private String orderId;
    private List<Item> items;
    private GeoLocation deliveryGeoLocation;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public GeoLocation getDeliveryGeoLocation() {
        return deliveryGeoLocation;
    }

    public void setDeliveryGeoLocation(GeoLocation deliveryGeoLocation) {
        this.deliveryGeoLocation = deliveryGeoLocation;
    }
}
