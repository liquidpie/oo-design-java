package com.vivek.splitwise.models.expense;

import com.vivek.splitwise.models.User;
import com.vivek.splitwise.models.split.EqualSplit;
import com.vivek.splitwise.models.split.Split;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }

        return true;
    }
}
