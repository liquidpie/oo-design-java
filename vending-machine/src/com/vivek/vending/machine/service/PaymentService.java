package com.vivek.vending.machine.service;

import com.vivek.vending.machine.model.Payment;

import java.math.BigDecimal;

/**
 * To be implemented by proxy classes. All service classes are created as singleton
 */
public interface PaymentService {

    BigDecimal makePayment(Payment payment);

}
