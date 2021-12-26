package com.vivek.inventory.persistence;

import com.vivek.inventory.domain.Order;
import com.vivek.inventory.domain.Product;
import com.vivek.inventory.domain.ReservedItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public final class DataStore {

    public static final Map<String, Integer> INVENTORY = new ConcurrentHashMap<>();
    public static final Map<String, Product> PRODUCT_DETAILS = new HashMap<>();
    public static final Map<String, List<String>> ORDERS = new HashMap<>();
    public static final Map<String, Order> ORDER_DETAILS = new HashMap<>();

    public static final Map<String, ReservedItem> RESERVED_INVENTORY = new ConcurrentHashMap<>();
    public static final PriorityBlockingQueue<Order> ORDERS_QUEUE = new PriorityBlockingQueue<>(1000, (o1, o2) -> (int) (o1.getCreatedAt() - o2.getCreatedAt()));

}
