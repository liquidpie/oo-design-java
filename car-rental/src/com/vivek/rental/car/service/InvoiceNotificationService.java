package com.vivek.rental.car.service;

import com.vivek.rental.car.model.reservation.InvoiceNotification;

public interface InvoiceNotificationService {
    void notifyUser(InvoiceNotification invoice);
}
