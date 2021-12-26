package com.vivek.inventory.service;

import com.vivek.inventory.domain.Order;
import com.vivek.inventory.domain.OrderStatus;
import com.vivek.inventory.persistence.DataStore;

import java.util.Timer;
import java.util.TimerTask;

public class HousekeepingService {

    private static final long DELAY = 0;
    private static final long PERIOD = 2000;
    private static final long TIMEOUT_MS = 1000;

    private InventoryService inventoryService;

    public HousekeepingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void schedule() {
        Timer time = new Timer();
        ScheduledTask st = new ScheduledTask();
        time.schedule(st, DELAY, PERIOD);
    }

    private class ScheduledTask extends TimerTask {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            var ordersQueue = DataStore.ORDERS_QUEUE;
            while (!ordersQueue.isEmpty() && (currentTime - ordersQueue.peek().getCreatedAt()) > TIMEOUT_MS) {
                Order order = ordersQueue.poll();
                order.setStatus(OrderStatus.CANCELLED);
                inventoryService.releaseReservedItems(order.getOrderId());
            }
        }
    }

}
