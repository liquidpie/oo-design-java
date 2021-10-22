package com.vivek.coffee.machine.inventory;

import com.vivek.coffee.machine.model.Beverage;
import com.vivek.coffee.machine.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    public Map<String, Integer> inventory = new HashMap<>();

    private InventoryManager() { }

    private static class InventoryManagerHolder {
        public static final InventoryManager instance = new InventoryManager();
    }

    public static InventoryManager getInstance() {
        return InventoryManagerHolder.instance;
    }

    // Making this thread-safe
    public synchronized boolean checkAndUpdateInventory(Beverage beverage, Recipe recipe) {
        Map<String, Integer> requiredIngredientMap = recipe.getIngredients();
        boolean isPossible = true;

        for (String ingredient : requiredIngredientMap.keySet()) {
            int ingredientInventoryCount = inventory.getOrDefault(ingredient, -1);
            if (ingredientInventoryCount <= 0) {
                System.out.println(beverage.getName() + " can't be prepared because " + ingredient + " is not available");
                isPossible = false;
                break;
            } else if (ingredientInventoryCount < requiredIngredientMap.get(ingredient)) {
                System.out.println(beverage.getName() + " can't be prepared because " + ingredient + " is not sufficient");
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            for (String ingredient : requiredIngredientMap.keySet()) {
                int existingInventory = inventory.getOrDefault(ingredient, 0);
                inventory.put(ingredient, existingInventory - requiredIngredientMap.get(ingredient));
            }
        }

        return isPossible;
    }

    public void addInventory(String ingredient, int quantity) {
        int existingInventory = inventory.getOrDefault(ingredient, 0);
        inventory.put(ingredient, existingInventory + quantity);
    }

    public void resetInventory() {
        inventory.clear();
    }

}
