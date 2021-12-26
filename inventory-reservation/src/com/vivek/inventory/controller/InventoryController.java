package com.vivek.inventory.controller;

import com.vivek.inventory.service.InventoryService;

public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void createProduct(String productId, String name, Integer count) {
        inventoryService.createProduct(productId, name, count);
    }

    public Integer getInventory(String productId) {
        return inventoryService.getInventory(productId);
    }

}
