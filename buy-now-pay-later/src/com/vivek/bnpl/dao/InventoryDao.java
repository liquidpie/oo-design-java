package com.vivek.bnpl.dao;

import com.vivek.bnpl.database.Database;

public class InventoryDao {

    public void addProduct(String productId, Integer quantity) {
        Database.INVENTORY.put(productId, quantity);
    }

    public void incrementInventory(String productId, Integer quantity) {
        if (!Database.INVENTORY.containsKey(productId)) {
            Database.INVENTORY.put(productId, 0);
        }
        Database.INVENTORY.put(productId, Database.INVENTORY.get(productId) + quantity);
    }

    public void decrementInventory(String productId, Integer quantity) {
        if (!Database.INVENTORY.containsKey(productId)) {
            return;
        }
        var remaining = Database.INVENTORY.get(productId) - quantity;
        Database.INVENTORY.put(productId, Math.max(remaining, 0));
    }

    public int getInventory(String productId) {
        return Database.INVENTORY.get(productId);
    }

    public void deleteInventory(String productId) {
        Database.INVENTORY.remove(productId);
    }

}
