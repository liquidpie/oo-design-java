package com.vivek.fooddelivery;

import com.vivek.fooddelivery.controllers.FoodMenuController;
import com.vivek.fooddelivery.datastore.FoodMenuData;
import com.vivek.fooddelivery.model.CuisineType;
import com.vivek.fooddelivery.model.MealType;
import com.vivek.fooddelivery.model.MenuItem;
import com.vivek.fooddelivery.services.FoodMenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuTest {
    FoodMenuController foodMenuController;

    @BeforeEach
    void setup() {
        FoodMenuService foodMenuService = new FoodMenuService(new FoodMenuData());
        foodMenuController = new FoodMenuController(foodMenuService);
    }

    @Test
    void FoodMenuFlowTest() {
        List<MenuItem> menuItemList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            menuItemList.add(TestHelper.buildMenuItem("ITEM" + i, "Item Name " + i, i * 1000));
        }
        foodMenuController.addMenuByRestaurantId("MENU1", "REST1", menuItemList);
        foodMenuController.addMenuByRestaurantId("MENU2", "REST2", menuItemList);
        foodMenuController.addMenuByRestaurantId("MENU1", "REST3", menuItemList);

        System.out.println(foodMenuController.getMenuById("MENU1"));

        System.out.println(foodMenuController.getMenuByRestaurantId("REST2"));

        List<MenuItem> menuItemList1 = new ArrayList<>();
        for (int i = 3; i <= 4; i++) {
            menuItemList1.add(TestHelper.buildMenuItem("ITEM" + i, "Item Name" + i, i * 1000));
        }
        foodMenuController.addMenuItemsByMenuId("MENU1", menuItemList1);

        foodMenuController.addMenuItemsByRestaurantId("REST2", menuItemList1);

        System.out.println(foodMenuController.getMenuItemsByRestaurantIdAndCuisine(
                "REST1", CuisineType.ITALIAN_CUISINE));

        System.out.println(foodMenuController.getMenuItemsByRestaurantIdAndMealType(
                "REST1", MealType.DINNER));


    }
}
