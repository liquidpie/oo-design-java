package com.vivek.online.shopping.model.payment;

import com.vivek.online.shopping.model.user.Account;

public class Payment {

    private String transactionId;
    private PaymentStatus status;
    private double amount;

    PaymentStatus processPayment(Account account, double amount) {
        return null;
    }

}
