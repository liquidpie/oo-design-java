package com.vivek.bnpl.dao;

import com.vivek.bnpl.database.Database;
import com.vivek.bnpl.domain.Order;
import com.vivek.bnpl.domain.OrderDetails;
import com.vivek.bnpl.domain.OrderKey;

import java.util.LinkedHashSet;
import java.util.Set;

public class OrderDao {

    public void createOrder(String userId, Order order, OrderDetails details) {
        Database.ORDER_DETAILS.put(new OrderKey(order.getId(), userId), details);
        var orders = Database.ORDERS.get(userId);
        if (orders == null) {
            orders = new LinkedHashSet<>();
        }
        orders.add(order);
        Database.ORDERS.put(userId, orders);
    }

    public Set<Order> getOrders(String userId) {
        return Database.ORDERS.get(userId);
    }

    public OrderDetails getOrderDetails(String userId, String orderId) {
        return Database.ORDER_DETAILS.get(new OrderKey(orderId, userId));
    }

}
