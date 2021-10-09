package com.vivek.vending.machine.model;

import java.math.BigDecimal;

public class Payment {
    private final BigDecimal amount;
    private final PaymentType paymentType;

    public Payment(BigDecimal amount, PaymentType paymentType) {
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
