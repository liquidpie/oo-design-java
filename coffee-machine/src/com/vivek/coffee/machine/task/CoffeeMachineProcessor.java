package com.vivek.coffee.machine.task;

import com.vivek.coffee.machine.inventory.InventoryManager;
import com.vivek.coffee.machine.inventory.RecipeManager;
import com.vivek.coffee.machine.model.Beverage;
import com.vivek.coffee.machine.model.Machine;
import com.vivek.coffee.machine.model.Recipe;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CoffeeMachineProcessor {

    private static final int MAX_QUEUED_REQUESTS = 100;
    private static CoffeeMachineProcessor instance;
    private Machine machine;
    private ThreadPoolExecutor executor;
    private InventoryManager inventoryManager;
    private RecipeManager recipeManager;

    public static CoffeeMachineProcessor getInstance(Machine machine) {
        if (instance == null) {
            instance = new CoffeeMachineProcessor(machine);
        }
        return instance;
    }

    private CoffeeMachineProcessor(Machine machine) {
        System.out.println("New Machine");
        this.machine = machine;
        int outlet = machine.getOutlets().getCount();
        executor = new ThreadPoolExecutor(outlet, outlet, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(MAX_QUEUED_REQUESTS));
        executor.setRejectedExecutionHandler(new RejectedBeverageTaskHandler());
    }

    public void process() {
        this.inventoryManager = InventoryManager.getInstance();
        this.recipeManager = RecipeManager.getInstance();

        Map<String, Integer> ingredients = machine.getIngredientQuantityMap();
        for (String ingredient : ingredients.keySet()) {
            inventoryManager.addInventory(ingredient, ingredients.get(ingredient));
        }

        recipeManager.loadRecipes(machine.getRecipes().stream()
                .collect(Collectors.toMap(Recipe::getBeverageName, Recipe::getIngredients)));

        for (String key : machine.getBeverages()) {
            Beverage beverage = new Beverage(key);
            instance.addBeverageRequest(beverage);
        }
    }

    private void addBeverageRequest(Beverage beverage) {
        BeverageMakerTask task = new BeverageMakerTask(beverage);
        executor.execute(task);
    }

    public void stopMachine() {
        executor.shutdown();
    }

    public void resetMachine() {
        stopMachine();
        inventoryManager.resetInventory();
    }

}
