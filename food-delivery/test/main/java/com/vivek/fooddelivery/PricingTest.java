package com.vivek.fooddelivery;

import com.vivek.fooddelivery.commands.AddCartCommandExecutor;
import com.vivek.fooddelivery.commands.CartCommandExecutor;
import com.vivek.fooddelivery.commands.RemoveCartCommandExecutor;
import com.vivek.fooddelivery.controllers.CartController;
import com.vivek.fooddelivery.controllers.FoodMenuController;
import com.vivek.fooddelivery.controllers.PricingController;
import com.vivek.fooddelivery.datastore.CartData;
import com.vivek.fooddelivery.datastore.FoodMenuData;
import com.vivek.fooddelivery.model.CartCommandType;
import com.vivek.fooddelivery.model.CouponCode;
import com.vivek.fooddelivery.model.MenuItem;
import com.vivek.fooddelivery.services.CartService;
import com.vivek.fooddelivery.services.FoodMenuService;
import com.vivek.fooddelivery.services.PricingService;
import com.vivek.fooddelivery.strategy.FiveHundredOffPricingStrategy;
import com.vivek.fooddelivery.strategy.PricingStrategy;
import com.vivek.fooddelivery.strategy.TwentyPercentOffPricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PricingTest {
    PricingController pricingController;
    FoodMenuController foodMenuController;
    CartController cartController;

    @BeforeEach
    void setup() {
        List<PricingStrategy> pricingStrategyList = List.of(new TwentyPercentOffPricingStrategy(),
                new FiveHundredOffPricingStrategy());
        CartData cartData = new CartData();
        FoodMenuService foodMenuService = new FoodMenuService(new FoodMenuData());
        List<CartCommandExecutor> commandExecutors = List.of(new AddCartCommandExecutor(foodMenuService, cartData),
                new RemoveCartCommandExecutor(cartData));
        CartService cartService = new CartService(cartData, commandExecutors, foodMenuService);
        PricingService pricingService = new PricingService(pricingStrategyList, cartService);
        pricingController = new PricingController(pricingService);
        cartController = new CartController(cartService);
        foodMenuController = new FoodMenuController(foodMenuService);
    }

    @Test
    void PricingTest(){
        List<MenuItem> menuItemList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            menuItemList.add(TestHelper.buildMenuItem("ITEM" + i, "Item Name " + i, i * 1000));
        }
        foodMenuController.addMenuByRestaurantId("MENU1", "REST1", menuItemList);
        foodMenuController.addMenuByRestaurantId("MENU2", "REST2", menuItemList);
        foodMenuController.addMenuByRestaurantId("MENU1", "REST3", menuItemList);

        cartController.updateCart("USER1","REST1","ITEM2", CartCommandType.ADD_ITEM);

        System.out.println(pricingController
                .getBill("USER1", "REST1", CouponCode.TWENTY_PERCENT_OFF));
    }
}
