package com.vivek.bookmyshow.domain;

import java.util.List;

public class Member extends SystemMember {

    public Booking makeBooking(Booking booking) {
        return booking;
    }

    public List<Booking> getBookings() {
        return null;
    }

}
