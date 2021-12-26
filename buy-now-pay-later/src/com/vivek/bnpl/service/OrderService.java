package com.vivek.bnpl.service;

import com.vivek.bnpl.dao.OrderDao;
import com.vivek.bnpl.domain.Cart;
import com.vivek.bnpl.domain.Order;
import com.vivek.bnpl.domain.OrderDetails;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order createOrder(String userId, Cart cart) {
        // order object
        Order order = null;

        orderDao.createOrder(userId, order, new OrderDetails(cart.getItems()));

        return order;

    }

    public Order getOrder() {

    }



}
