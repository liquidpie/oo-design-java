package com.vivek.inventory.service;

import com.vivek.inventory.domain.Product;
import com.vivek.inventory.domain.ReservedItem;
import com.vivek.inventory.persistence.DataStore;

import java.util.concurrent.atomic.AtomicInteger;

public final class InventoryService {

    public void createProduct(String productId, String name, Integer count) {
        DataStore.PRODUCT_DETAILS.put(productId, new Product(productId, name));
        DataStore.INVENTORY.put(productId, count);
    }

    public Integer getInventory(final String productId) {
        return DataStore.INVENTORY.get(productId);
    }

    public void reserveItems(String productId, Integer count, String orderId) {
        AtomicInteger quantity = new AtomicInteger(DataStore.INVENTORY.get(productId));
        quantity.set(quantity.intValue() - count);
        DataStore.INVENTORY.put(productId, quantity.get());
        ReservedItem reservedItem = new ReservedItem(productId, count);
        DataStore.RESERVED_INVENTORY.put(orderId, reservedItem);
        DataStore.ORDERS_QUEUE.add(DataStore.ORDER_DETAILS.get(orderId));
    }

    public void releaseReservedItems(String orderId) {
        ReservedItem reservedItem = DataStore.RESERVED_INVENTORY.get(orderId);
        if (reservedItem != null) {
            AtomicInteger quantity = new AtomicInteger(DataStore.INVENTORY.get(reservedItem.getProductId()));
            quantity.set(quantity.intValue() + reservedItem.getCount());
            DataStore.INVENTORY.put(reservedItem.getProductId(), quantity.get());
            DataStore.RESERVED_INVENTORY.remove(orderId);
        }
    }

}
