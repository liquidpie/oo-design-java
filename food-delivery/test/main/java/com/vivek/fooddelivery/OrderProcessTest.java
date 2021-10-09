package com.vivek.fooddelivery;

import com.vivek.fooddelivery.commands.CancelOrderCommandExecutor;
import com.vivek.fooddelivery.commands.OrderCommandExecutor;
import com.vivek.fooddelivery.commands.PlaceOrderCommandExecutor;
import com.vivek.fooddelivery.controllers.OrderController;
import com.vivek.fooddelivery.datastore.OrderData;
import com.vivek.fooddelivery.model.MenuItem;
import com.vivek.fooddelivery.model.Order;
import com.vivek.fooddelivery.model.OrderCommandType;
import com.vivek.fooddelivery.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessTest {
    OrderController orderController;

    @BeforeEach
    void setup() {
        OrderData orderData = new OrderData();
        List<OrderCommandExecutor> orderCommandExecutorList = List.of(new PlaceOrderCommandExecutor(orderData),
                new CancelOrderCommandExecutor(orderData));
        OrderService orderService = new OrderService(orderData, orderCommandExecutorList);
        orderController = new OrderController(orderService);
    }

    @Test
    void OrderFlowTest() {
        List<MenuItem> menuItemList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            menuItemList.add(TestHelper.buildMenuItem("ITEM" + i, "Item Name " + i, i * 1000));
        }
        Order order = new Order("ORDER1", "USER1", "REST1", menuItemList);
        orderController.updateOrder(order, OrderCommandType.PLACE);

        System.out.println(orderController.getAllOrders("USER1"));

        System.out.println(orderController.getOrderById("ORDER1"));

        System.out.println(orderController.getAllOrdersByRestaurantId("USER1","REST1"));

        orderController.updateOrder(order, OrderCommandType.CANCEL);

        System.out.println(orderController.getOrderById("ORDER1"));
    }
}
