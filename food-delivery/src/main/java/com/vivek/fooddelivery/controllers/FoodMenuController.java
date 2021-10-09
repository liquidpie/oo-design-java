package com.vivek.fooddelivery.controllers;

import com.vivek.fooddelivery.model.CuisineType;
import com.vivek.fooddelivery.model.FoodMenu;
import com.vivek.fooddelivery.model.MealType;
import com.vivek.fooddelivery.model.MenuItem;
import com.vivek.fooddelivery.services.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodMenuController {
    private FoodMenuService foodMenuService;

    @Autowired
    public FoodMenuController(FoodMenuService foodMenuService) {
        this.foodMenuService = foodMenuService;
    }

    @PostMapping(value = "/restaurant/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuByRestaurantId(final String menuId, final String restaurantId,
                                      final List<MenuItem> menuItemList) {
        foodMenuService.addMenuByRestaurantId(menuId, restaurantId, menuItemList);
    }

    @GetMapping(value = "/menu/{menuId}")
    public FoodMenu getMenuById(final String menuId) {
        return foodMenuService.getMenuById(menuId);
    }

    @GetMapping(value = "/restaurant/{restaurantId}/menu")
    public FoodMenu getMenuByRestaurantId(final String restaurantId) {
        return foodMenuService.getMenuByRestaurantId(restaurantId);
    }

    @PutMapping(value = "/menu/items")
    public void addMenuItemsByMenuId(final String menuId, final List<MenuItem> menuItemList) {
        foodMenuService.addMenuItemsByMenuId(menuId, menuItemList);
    }

    @PutMapping(value = "/restaurant/{restaurantId}/menu/items")
    public void addMenuItemsByRestaurantId(final String restaurantId, final List<MenuItem> menuItemList) {
        foodMenuService.addMenuItemsByRestaurantId(restaurantId, menuItemList);
    }

    @GetMapping(value = "/restaurant/{restaurantId}/menu/cuisine/{cuisineType}")
    public List<MenuItem> getMenuItemsByRestaurantIdAndCuisine(final String restaurantId,
                                                               final CuisineType cuisineType) {
        return foodMenuService.getMenuItemsByRestaurantIdAndCuisine(restaurantId, cuisineType);
    }

    @GetMapping(value = "/restaurant/{restaurantId}/menu/meal/{mealType}")
    public List<MenuItem> getMenuItemsByRestaurantIdAndMealType(final String restaurantId,
                                                                final MealType mealType) {
        return foodMenuService.getMenuItemsByRestaurantIdAndMealType(restaurantId, mealType);
    }
}
