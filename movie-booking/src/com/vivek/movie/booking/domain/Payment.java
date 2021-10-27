package com.vivek.movie.booking.domain;

import com.vivek.movie.booking.enums.PaymentStatus;

import java.util.Date;

public class Payment {

    double amount;
    Date paymentDate;
    int transactionId;
    PaymentStatus status;

}
