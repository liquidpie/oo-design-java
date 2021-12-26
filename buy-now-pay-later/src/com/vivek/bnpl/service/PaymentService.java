package com.vivek.bnpl.service;

import com.vivek.bnpl.dao.InventoryDao;
import com.vivek.bnpl.dao.PaymentDao;
import com.vivek.bnpl.dao.UserDao;
import com.vivek.bnpl.domain.Order;
import com.vivek.bnpl.domain.OrderItem;
import com.vivek.bnpl.domain.Payment;
import com.vivek.bnpl.util.IdGenerator;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PaymentService {

    private final InventoryDao inventoryDao;
    private final PaymentDao paymentDao;
    private final UserDao userDao;

    public PaymentService(InventoryDao inventoryDao, PaymentDao paymentDao, UserDao userDao) {
        this.inventoryDao = inventoryDao;
        this.paymentDao = paymentDao;
        this.userDao = userDao;
    }

    public void payNow(String userId, Order order) {
        for (OrderItem item : order.getDetails().getItems()) {
            inventoryDao.decrementInventory(item.getProductId(), item.getQuantity());
        }

        var transactionId = IdGenerator.newId();
        var timestamp = new Timestamp(System.currentTimeMillis());
        var transaction = new Payment(transactionId, order.getId(), userId, order.getPaymentType(), order.getTotalAmount(), BigInteger.ZERO, timestamp);
        paymentDao.createTransaction(transaction);

        System.out.println("Payment completed");
    }

    public boolean buyNowPayLater(String userId, Order order) {
        if (userDao.isBlackListed(userId)) {
            System.out.println("Buy Now Pay Later option not available for the user");
            return false;
        }

        if (userDao.getCreditLimit(userId).compareTo(order.getTotalAmount()) < 0) {
            System.out.println("User doesn't have enough credit limit");
            return false;
        }

        for (OrderItem item : order.getDetails().getItems()) {
            inventoryDao.decrementInventory(item.getProductId(), item.getQuantity());
        }

        var transactionId = IdGenerator.newId();
        var timestamp = new Timestamp(System.currentTimeMillis());
        var transaction = new Payment(transactionId, order.getId(), userId, order.getPaymentType(), order.getTotalAmount(), order.getTotalAmount(), timestamp);
        paymentDao.createTransaction(transaction);

        userDao.updateCreditLimit(userId, order.getTotalAmount());

        return true;
    }

    public void clearDues(String userId, String orderId, BigInteger duePaid) {
        paymentDao.payDuesForBNPL(userId, orderId, transactionId, duePaid);
    }

}
