package com.vivek.bookmyshow.domain;

import com.vivek.bookmyshow.enums.BookingStatus;
import com.vivek.bookmyshow.enums.PaymentStatus;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Booking {

    String bookingId;
    Date bookingDate;
    Member member;
    Auditorium auditorium;
    Show show;
    BookingStatus bookingStatus;
    double totalAmount;
    List<Seat> seats;
    Payment payment;

    public boolean makePayment(Payment payment) {
        return false;
    }

}
