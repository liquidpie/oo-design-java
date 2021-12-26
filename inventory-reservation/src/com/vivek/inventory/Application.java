package com.vivek.inventory;

import com.vivek.inventory.controller.InventoryController;
import com.vivek.inventory.controller.OrderController;
import com.vivek.inventory.service.HousekeepingService;
import com.vivek.inventory.service.InventoryService;
import com.vivek.inventory.service.OrderService;

public class Application {

    public static void main(String[] args) throws Exception {
        // Dependencies
        InventoryService is = new InventoryService();
        OrderService os = new OrderService();
        InventoryController inventoryController = new InventoryController(is);
        OrderController orderController = new OrderController(os, is);
        HousekeepingService housekeepingService = new HousekeepingService(is);
        housekeepingService.schedule();

        // Users
        String user1 = "1";

        // Usecase: Create Product
        inventoryController.createProduct("p1", "product 1", 2);
        inventoryController.createProduct("p2", "product 2", 2);
        inventoryController.createProduct("p3", "product 3", 2);

        // Usecase: Fetch product quantity
        System.out.println(inventoryController.getInventory("p1"));

        // Usecase: User places Order
        String orderId = orderController.placeOrder(user1, "p1", 1);

        // Usecase: User initiates payment, block inventory for order
        orderController.blockInventory("p1", 1, orderId);
        System.out.println(inventoryController.getInventory("p1"));

        // Usecase: User completes payment in time limit and confirms order
//        orderController.confirmOrder(orderId);
//        System.out.println(inventoryController.getInventory("p1"));

        // Usecase: User abandons payment, wait for time limit
        Thread.sleep(5000);
        System.out.println(inventoryController.getInventory("p1"));

    }


}
