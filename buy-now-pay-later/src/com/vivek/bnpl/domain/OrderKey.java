package com.vivek.bnpl.domain;

import java.util.Objects;

public final class OrderKey {

    private final String orderId;
    private final String userId;

    public OrderKey(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
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
        OrderKey orderKey = (OrderKey) o;
        return Objects.equals(orderId, orderKey.orderId) && Objects.equals(userId, orderKey.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId);
    }

    @Override
    public String toString() {
        return "OrderKey{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
