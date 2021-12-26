package com.vivek.inventory.domain;

import java.util.List;
import java.util.Objects;

public final class OrderDetails {

    private final List<OrderItem> items;

    public OrderDetails(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "items=" + items +
                '}';
    }
}
