package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.cart.Cart;

public interface Rule {

    boolean evaluate(Cart cart);
    boolean shouldRun();

}
