package com.vivek.coffee.machine.inventory;

import java.util.HashMap;
import java.util.Map;

public class RecipeManager {

    public static final Map<String, Map<String, Integer>> recipes = new HashMap<>();

    private RecipeManager() { }

    private static class RecipeManagerHolder {
        public static final RecipeManager instance = new RecipeManager();
    }

    public static RecipeManager getInstance() {
        return RecipeManagerHolder.instance;
    }

    public void loadRecipes(Map<String, Map<String, Integer>> newRecipes) {
        recipes.putAll(newRecipes);
    }

    public void addRecipe(String beverageName, Map<String, Integer> ingredients) {
        recipes.put(beverageName, ingredients);
    }

}
