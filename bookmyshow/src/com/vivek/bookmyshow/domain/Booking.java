package com.vivek.bookmyshow.domain;

import com.vivek.bookmyshow.enums.PaymentStatus;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Booking {

    private int bookingId;
    private int userId;
    private int movieId;
    private List<Movie> bookedSeats;
    private int amount;
    private PaymentStatus status_of_payment;
    private Date booked_date;
    private Time movie_timing;

}
