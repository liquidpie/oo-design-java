package com.vivek.fooddelivery.commands;

import com.vivek.fooddelivery.datastore.OrderData;
import com.vivek.fooddelivery.model.Order;
import com.vivek.fooddelivery.model.OrderCommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderCommandExecutor extends OrderCommandExecutor {
    private OrderData orderData;

    @Autowired
    public CancelOrderCommandExecutor(OrderData orderData) {
        this.orderData = orderData;
    }

    @Override
    public boolean isValid(Order order) {
        if (!orderData.getOrderById().containsKey(order.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Order order) {
        order.markOrderCancelled();
    }

    @Override
    public boolean isApplicable(OrderCommandType orderCommandType) {
        return orderCommandType == OrderCommandType.CANCEL;
    }
}
