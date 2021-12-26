package com.vivek.bnpl.domain;

import java.util.Objects;

public class TransactionKey {

    private final String transactionId;
    private final String orderId;
    private final String userId;

    public TransactionKey(String transactionId, String orderId, String userId) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionKey that = (TransactionKey) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, orderId, userId);
    }

    @Override
    public String toString() {
        return "TransactionKey{" +
                "transactionId='" + transactionId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
