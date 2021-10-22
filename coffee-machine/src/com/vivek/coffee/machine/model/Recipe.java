package com.vivek.coffee.machine.model;

import java.util.Map;

public class Recipe {

    private final String beverageName;
    private final Map<String, Integer> ingredients;

    public Recipe(String beverageName, Map<String, Integer> ingredients) {
        this.beverageName = beverageName;
        this.ingredients = ingredients;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
}
