package com.vivek.fooddelivery.controllers;

import com.vivek.fooddelivery.model.Bill;
import com.vivek.fooddelivery.model.CouponCode;
import com.vivek.fooddelivery.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricingController {
    private PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping(value = "/user/{userId}/cart/{couponCode}")
    public Bill getBill(final String userId, final String restaurantId, final CouponCode couponCode) {
        return pricingService.getBill(userId, restaurantId, couponCode);
    }
}