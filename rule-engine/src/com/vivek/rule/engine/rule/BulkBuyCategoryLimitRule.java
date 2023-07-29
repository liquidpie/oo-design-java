package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.cart.Cart;
import com.vivek.rule.engine.cart.CartItem;
import com.vivek.rule.engine.cart.ProductCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkBuyCategoryLimitRule implements Rule {

    private static final int BUY_LIMIT = 5;
    private boolean isActive;

    public BulkBuyCategoryLimitRule() {
        this(true);
    }

    public BulkBuyCategoryLimitRule(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean evaluate(Cart cart) {
        if (!shouldRun()) {
            return true;
        }

        List<CartItem> items = cart.getItems();
        Map<ProductCategory, Integer> categoryToQty = new HashMap<>();
        for (CartItem item : items) {
            item.getCategories().forEach(productCategory -> {
                categoryToQty.put(productCategory, categoryToQty.getOrDefault(productCategory, 0) + 1);
            });
        }

        for (Map.Entry<ProductCategory, Integer> entry : categoryToQty.entrySet()) {
            if (entry.getValue() > BUY_LIMIT) {
                System.out.printf("%s product category has more than %s quantity", entry.getKey(), BUY_LIMIT);
                return false;
            }
        }

        return true;

    }

    @Override
    public boolean shouldRun() {
        return isActive;
    }

    public void deactivate() {
        isActive = false;
    }

    public String toString() {
        return BulkBuyCategoryLimitRule.class.getSimpleName();
    }
}