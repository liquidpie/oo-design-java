package com.vivek.fooddelivery.strategy;

import com.vivek.fooddelivery.model.Bill;
import com.vivek.fooddelivery.model.CouponCode;
import com.vivek.fooddelivery.model.MenuItem;

import java.util.List;


public interface PricingStrategy {
    Bill generateBill(List<MenuItem> menuItemList);

    boolean isApplicable(CouponCode couponCode);
}
