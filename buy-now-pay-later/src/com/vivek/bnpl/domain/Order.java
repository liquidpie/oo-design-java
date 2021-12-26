package com.vivek.bnpl.domain;

import com.vivek.myntra.domain.enums.PaymentType;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    private final String id;
    private final String userId;
    private final OrderDetails details;
    private final BigInteger totalAmount;
    private final PaymentType paymentType;

    private final Timestamp timestamp;

    public Order(String id, String userId, OrderDetails details, BigInteger totalAmount, PaymentType paymentType, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.details = details;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public OrderDetails getDetails() {
        return details;
    }

    public BigInteger getTotalAmount() {
        return totalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(userId, order.userId) && Objects.equals(details, order.details) && Objects.equals(totalAmount, order.totalAmount) && paymentType == order.paymentType && Objects.equals(timestamp, order.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, details, totalAmount, paymentType, timestamp);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", details=" + details +
                ", totalAmount=" + totalAmount +
                ", paymentType=" + paymentType +
                ", timestamp=" + timestamp +
                '}';
    }
}
