package com.vivek.bnpl.dao;

import com.vivek.bnpl.database.Database;
import com.vivek.bnpl.domain.Payment;
import com.vivek.bnpl.domain.TransactionKey;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PaymentDao {

    public void createTransaction(Payment payment) {
        Database.PAYMENTS.put(new TransactionKey(payment.getId(), payment.getOrderId(), payment.getUserId()), payment);
    }

    public void payDuesForBNPL(String userId, String orderId, String transactionId, BigInteger dues) {
        var transaction = Database.PAYMENTS.get(new TransactionKey(transactionId, orderId, userId));
        transaction.setDues(transaction.getDues().subtract(dues));
        transaction.setLastPaymentTimestamp(new Timestamp(System.currentTimeMillis()));
    }

    public BigInteger getRemainingDues(String userId, String orderId, String transactionId) {
        var transaction = Database.PAYMENTS.get(new TransactionKey(transactionId, orderId, userId));
        return transaction.getDues();
    }

}
