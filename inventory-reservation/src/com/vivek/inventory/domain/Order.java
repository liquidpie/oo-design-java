package com.vivek.inventory.domain;

public final class Order {

    private final String orderId;
    private final String userId;
    private final OrderDetails details;
    private final long createdAt;
    private OrderStatus status;

    public Order(String orderId, String userId, OrderDetails details) {
        this.orderId = orderId;
        this.userId = userId;
        this.details = details;
        this.createdAt = System.currentTimeMillis();
        this.status = OrderStatus.CREATED;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public OrderDetails getDetails() {
        return details;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
