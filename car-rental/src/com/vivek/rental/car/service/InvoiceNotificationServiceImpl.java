package com.vivek.rental.car.service;

import com.vivek.rental.car.model.account.User;
import com.vivek.rental.car.model.common.NotificationStatus;
import com.vivek.rental.car.model.reservation.InvoiceNotification;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.repository.UserRepository;
import com.vivek.rental.car.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
