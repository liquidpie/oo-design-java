package com.vivek.inventory.controller;

import com.vivek.inventory.service.InventoryService;
import com.vivek.inventory.service.OrderService;

public class OrderController {

    private final OrderService orderService;
    private final InventoryService inventoryService;

    public OrderController(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    public String placeOrder(String userId, String productId, Integer count) {
        return orderService.placeOrder(userId, productId, count);
    }

    public void blockInventory(String productId, Integer count, String orderId) {
        inventoryService.reserveItems(productId, count, orderId);
    }

    public void confirmOrder(String orderId) {
        orderService.confirmOrder(orderId);
    }

}
