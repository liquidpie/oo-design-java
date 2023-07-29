package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.cart.Cart;
import com.vivek.rule.engine.cart.CartItem;

import java.util.List;

public class BulkBuyLimitRule implements Rule {

    private static final int BUY_LIMIT = 10;

    private boolean isActive;

    public BulkBuyLimitRule() {
        this(true);
    }

    public BulkBuyLimitRule(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean evaluate(Cart cart) {
        if (!shouldRun()) {
            return true;
        }

        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            if (item.getQuantity() > BUY_LIMIT) {
                System.out.printf("%s product id has more than %s quantity", item.getProductId(), BUY_LIMIT);
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
        return BulkBuyLimitRule.class.getSimpleName();
    }
}
