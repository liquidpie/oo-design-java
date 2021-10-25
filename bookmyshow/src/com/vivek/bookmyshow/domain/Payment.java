package com.vivek.bookmyshow.domain;

import com.vivek.bookmyshow.enums.PaymentStatus;

import java.util.Date;

public class Payment {

    double amount;
    Date paymentDate;
    int transactionId;
    PaymentStatus status;

}
