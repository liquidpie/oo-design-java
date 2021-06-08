package com.vivek.splitwise.models.split;

import com.vivek.splitwise.models.User;

public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }

}
