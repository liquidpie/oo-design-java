package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.cart.Cart;

public interface RuleEngine {

    void addRule(Rule rule);
    void removeRule(Rule rule);

    EvaluationOutput evaluate(Cart cart);

}
