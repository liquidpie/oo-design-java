package com.vivek.rental.car.service;

import com.vivek.rental.car.model.reservation.Invoice;
import com.vivek.rental.car.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
