package com.vivek.rule.engine;

import com.vivek.rule.engine.cart.Cart;
import com.vivek.rule.engine.cart.ProductCategory;
import com.vivek.rule.engine.rule.*;

public class Main {

    public static void main(String[] args) {
        Rule bulkBuyRule = RuleFactory.getRule(RuleType.BULK_BUY_LIMIT);
        Rule bulkBuyCategoryRule = RuleFactory.getRule(RuleType.BULK_BUY_CATEGORY_LIMIT);
        RuleEngine engine = new RuleEngineImpl();
        engine.addRule(bulkBuyRule);
        engine.addRule(bulkBuyCategoryRule);

        Cart cart = new Cart();
        cart.addItemToCart("1", ProductCategory.Paracetamol, 3);
        cart.addItemToCart("2", ProductCategory.analgesic, 3);
        cart.addItemToCart("3", ProductCategory.chocolate, 12);
        cart.addItemToCart("4", ProductCategory.Paracetamol, 2);

        EvaluationOutput result = engine.evaluate(cart);
        System.out.println(result);
    }

}