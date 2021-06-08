package com.vivek.splitwise.models.expense;

import com.vivek.splitwise.models.User;
import com.vivek.splitwise.models.split.ExactSplit;
import com.vivek.splitwise.models.split.Split;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        return totalAmount == sumSplitAmount;
    }

}
