package com.vivek.online.shopping.model.order;

import com.vivek.online.shopping.model.payment.Payment;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private String orderNumber;
    private OrderStatus status;
    private LocalDate orderDate;
    private List<OrderLog> orderLogs;

    void makePayment(Payment payment) { }

    void sendForShipment() { }

    void addOrderLog(OrderLog orderLog) { }

}
