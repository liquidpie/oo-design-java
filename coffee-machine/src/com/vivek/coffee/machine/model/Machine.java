package com.vivek.coffee.machine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Machine {

    private final Outlet outlets;
    private final Map<String, Integer> ingredientQuantityMap;
    private final List<Recipe> recipes;
    private final List<String> beverages;

    public Machine(int outlets, Map<String, Integer> ingredientQuantityMap, List<Recipe> recipes) {
        this.outlets = new Outlet(outlets);
        this.ingredientQuantityMap = ingredientQuantityMap;
        this.recipes = recipes;
        this.beverages = new ArrayList<>();
    }

    public Outlet getOutlets() {
        return outlets;
    }

    public Map<String, Integer> getIngredientQuantityMap() {
        return ingredientQuantityMap;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<String> getBeverages() {
        return beverages;
    }

    public void addBeverageRequest(String name) {
        beverages.add(name);
    }
}
