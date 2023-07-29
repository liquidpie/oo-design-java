package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.exception.InvalidArgumentException;

public class RuleFactory {

    private RuleFactory() { }
    public static Rule getRule(RuleType type) {
        switch(type) {
            case BULK_BUY_LIMIT:
                return new BulkBuyLimitRule();
            case BULK_BUY_CATEGORY_LIMIT:
                return new BulkBuyCategoryLimitRule();
            default:
                throw new InvalidArgumentException("no such rule found");
        }
    }

}
