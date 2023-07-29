package com.vivek.rule.engine.rule;

import com.vivek.rule.engine.cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class RuleEngineImpl implements RuleEngine {

    private final List<Rule> rules;

    public RuleEngineImpl() {
        this(new ArrayList<>());
    }

    public RuleEngineImpl(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public void removeRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public EvaluationOutput evaluate(Cart cart) {
        if (rules.isEmpty()) {
            System.out.println("No matching rules found!");
            return EvaluationOutput.MET;
        }

        for (Rule rule : rules) {
            if (rule.shouldRun()) {
                var result = rule.evaluate(cart);
                if (!result) {
                    System.out.println("Cart not adhering to the rule: " + rule);
                    return EvaluationOutput.BREACHED;
                }
            }
        }

        return EvaluationOutput.MET;
    }

}