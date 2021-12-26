package com.vivek.bnpl.database;

import com.vivek.myntra.domain.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Database {

    private Database() { }

    public static final Map<String, Integer> INVENTORY = new HashMap<>();
    public static final Map<String, Product> PRODUCT_DETAILS = new HashMap<>();
    public static final Map<String, Set<Order>> ORDERS = new HashMap<>();
    public static final Map<OrderKey, OrderDetails> ORDER_DETAILS = new HashMap<>();
    public static final Map<TransactionKey, Payment> PAYMENTS = new HashMap<>();
    public static final Map<String, User> USERS = new HashMap<>();
    public static final Set<String> BLACKLISTED_USERS = new HashSet<>();

}
