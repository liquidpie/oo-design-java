package com.vivek.inventory.service;

import com.vivek.inventory.domain.Order;
import com.vivek.inventory.domain.OrderDetails;
import com.vivek.inventory.domain.OrderItem;
import com.vivek.inventory.domain.OrderStatus;
import com.vivek.inventory.persistence.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class OrderService {

    public String placeOrder(String userId, String productId, Integer count) {
        OrderItem item = new OrderItem(productId, count);
        OrderDetails orderDetails = new OrderDetails(List.of(item));
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, userId, orderDetails);
        DataStore.ORDER_DETAILS.put(orderId, order);
        List<String> ordersFromUser = DataStore.ORDERS.getOrDefault(userId, new ArrayList<>());
        ordersFromUser.add(orderId);
        DataStore.ORDERS.put(userId, ordersFromUser);
        return order.getOrderId();
    }

    public void confirmOrder(String orderId) {
        Order order = DataStore.ORDER_DETAILS.get(orderId);
        if (order == null)
            throw new IllegalArgumentException("Order Id not found");
        if (DataStore.RESERVED_INVENTORY.containsKey(orderId)) {
            order.setStatus(OrderStatus.COMPLETED);
            DataStore.RESERVED_INVENTORY.remove(orderId);
        } else {
            throw new IllegalStateException("No reserved items found for order");
        }
    }

}
