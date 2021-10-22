package com.vivek.coffee.machine.task;

import com.vivek.coffee.machine.inventory.InventoryManager;
import com.vivek.coffee.machine.inventory.RecipeManager;
import com.vivek.coffee.machine.model.Beverage;
import com.vivek.coffee.machine.model.Recipe;

public class BeverageMakerTask implements Runnable {

    private final Beverage beverage;

    public BeverageMakerTask(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public void run() {
        Recipe recipe = new Recipe(beverage.getName(), RecipeManager.recipes.get(beverage.getName()));
        if (InventoryManager.getInstance().checkAndUpdateInventory(beverage, recipe)) {
            System.out.println(beverage + " is prepared!!");
        }
    }

    @Override
    public String toString() {
        return beverage.getName();
    }
}
