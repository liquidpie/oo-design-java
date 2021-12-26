package com.vivek.bnpl.domain;

import com.vivek.myntra.domain.enums.PaymentType;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class Payment {

    private final String id;
    private final String orderId;
    private final String userId;
    private final PaymentType type;
    private final BigInteger totalAmount;
    private BigInteger dues;
    private Timestamp lastPaymentTimestamp;

    public Payment(String id, String orderId, String userId, PaymentType type, BigInteger totalAmount, BigInteger dues, Timestamp lastPaymentTimestamp) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.type = type;
        this.totalAmount = totalAmount;
        this.dues = dues;
        this.lastPaymentTimestamp = lastPaymentTimestamp;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public PaymentType getType() {
        return type;
    }

    public BigInteger getTotalAmount() {
        return totalAmount;
    }

    public BigInteger getDues() {
        return dues;
    }

    public Timestamp getLastPaymentTimestamp() {
        return lastPaymentTimestamp;
    }

    public void setDues(BigInteger dues) {
        this.dues = dues;
    }

    public void setLastPaymentTimestamp(Timestamp lastPaymentTimestamp) {
        this.lastPaymentTimestamp = lastPaymentTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(orderId, payment.orderId) && Objects.equals(userId, payment.userId) && type == payment.type && Objects.equals(totalAmount, payment.totalAmount) && Objects.equals(dues, payment.dues) && Objects.equals(lastPaymentTimestamp, payment.lastPaymentTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, userId, type, totalAmount, dues, lastPaymentTimestamp);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", totalAmount=" + totalAmount +
                ", dues=" + dues +
                ", lastPaymentTimestamp=" + lastPaymentTimestamp +
                '}';
    }
}
