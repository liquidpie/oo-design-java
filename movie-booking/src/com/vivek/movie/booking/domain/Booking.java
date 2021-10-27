package com.vivek.movie.booking.domain;

import com.vivek.movie.booking.enums.BookingStatus;

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
