package com.vivek.rental.car.service;

import com.vivek.rental.car.model.account.User;
import com.vivek.rental.car.model.common.NotificationStatus;
import com.vivek.rental.car.model.reservation.ReservationReminder;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.repository.UserRepository;
import com.vivek.rental.car.repository.VehicleReservationRepository;

public class BookingReminderServiceImpl implements BookingReminderService {

    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public void notifyUser(ReservationReminder reservationReminder) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminder.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminder.setNotificationStatus(NotificationStatus.SENT);
    }
}
