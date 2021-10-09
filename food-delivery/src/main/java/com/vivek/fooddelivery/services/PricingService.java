package com.vivek.fooddelivery.services;

import com.vivek.fooddelivery.model.Bill;
import com.vivek.fooddelivery.model.CouponCode;
import com.vivek.fooddelivery.model.MenuItem;
import com.vivek.fooddelivery.strategy.PricingStrategy;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {
    private List<PricingStrategy> pricingStrategyList;
    private CartService cartService;

    @Autowired
    public PricingService(List<PricingStrategy> pricingStrategyList, CartService cartService) {
        this.pricingStrategyList = pricingStrategyList;
        this.cartService = cartService;
    }

    public Bill getBill(@NonNull final String userId, @NonNull final String restaurantId,
                        @NonNull final CouponCode couponCode) {
        List<MenuItem> menuItemList = cartService.getAllItemsOfCart(userId, restaurantId);
        return pricingStrategyList.stream().filter(pricingStrategy -> pricingStrategy.isApplicable(couponCode))
                .findAny().get().generateBill(menuItemList);
    }
}
