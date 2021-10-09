package com.vivek.rental.car.service;

import com.vivek.rental.car.model.account.User;
import com.vivek.rental.car.model.reservation.Invoice;
import com.vivek.rental.car.model.reservation.VehicleFixedCosts;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.model.vehicle.HireableVehicle;
import com.vivek.rental.car.repository.UserRepository;
import com.vivek.rental.car.repository.VehicleRepository;

import java.util.UUID;

public class InvoiceBuilderUtil {

    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        invoice.setUsageCharges(fixedCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(fixedCost + taxes);
        return invoice;
    }
}
