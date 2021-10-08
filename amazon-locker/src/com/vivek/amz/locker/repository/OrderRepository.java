package com.vivek.amz.locker.repository;

import com.vivek.amz.locker.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    public static Map<String, Order> orderMap = new HashMap<>();

    public static Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }

}
