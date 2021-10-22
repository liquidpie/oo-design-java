package com.vivek.coffee.machine;

import com.vivek.coffee.machine.model.Machine;
import com.vivek.coffee.machine.model.Recipe;
import com.vivek.coffee.machine.task.CoffeeMachineProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachineApplication {

    public static void main(String[] args) {
        Map<String, Integer> ingredients = new HashMap<>();
        ingredients.put("hot_water", 500);
        ingredients.put("hot_milk", 500);
        ingredients.put("ginger_syrup", 100);
        ingredients.put("sugar_syrup", 100);
        ingredients.put("tea_leaves_syrup", 100);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("hot_tea", Map.of(
                "hot_water", 200,
                "hot_milk", 100,
                "ginger_syrup", 10,
                "sugar_syrup", 10,
                "tea_leaves_syrup", 30)));
        recipes.add(new Recipe("hot_coffee", Map.of(
                "hot_water", 100,
                "ginger_syrup", 30,
                "hot_milk", 400,
                "sugar_syrup", 50,
                "tea_leaves_syrup", 30)));
        recipes.add(new Recipe("black_tea", Map.of(
                "hot_water", 300,
                "ginger_syrup", 30,
                "sugar_syrup", 50,
                "tea_leaves_syrup", 30)));
        recipes.add(new Recipe("green_tea", Map.of(
                "hot_water", 100,
                "ginger_syrup", 30,
                "sugar_syrup", 50,
                "green_mixture", 30)));

        Machine machine = new Machine(1, ingredients, recipes);
        machine.addBeverageRequest("hot_tea");
        machine.addBeverageRequest("hot_coffee");
        machine.addBeverageRequest("black_tea");
        machine.addBeverageRequest("green_tea");

        CoffeeMachineProcessor processor = CoffeeMachineProcessor.getInstance(machine);
        processor.process();
//        processor.resetMachine();
    }

}
