package com.vivek.amz.locker.model;

public class Notification {

    private final String customerId;
    private final String orderId;
    private final String lockerId;
    private final String code;

    public Notification(String customerId, String orderId, String lockerId, String code) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.lockerId = lockerId;
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getLockerId() {
        return lockerId;
    }

    public String getCode() {
        return code;
    }
}
