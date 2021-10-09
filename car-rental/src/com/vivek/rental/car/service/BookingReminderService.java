package com.vivek.rental.car.service;

import com.vivek.rental.car.model.reservation.ReservationReminder;

public interface BookingReminderService {
    void notifyUser(ReservationReminder reservationReminder);
}
