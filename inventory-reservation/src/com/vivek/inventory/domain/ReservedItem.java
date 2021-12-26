package com.vivek.inventory.domain;

public class ReservedItem {

    private final String productId;
    private final int count;
    private final long placedAt;

    public ReservedItem(String productId, int count) {
        this.productId = productId;
        this.count = count;
        this.placedAt = System.currentTimeMillis();
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public long getPlacedAt() {
        return placedAt;
    }
}
