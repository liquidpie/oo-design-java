package com.vivek.rental.car.service;

import com.vivek.rental.car.model.account.User;
import com.vivek.rental.car.model.reservation.Invoice;
import com.vivek.rental.car.model.reservation.VehicleFixedCosts;
import com.vivek.rental.car.model.reservation.VehicleMonthlyCosts;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.model.vehicle.HireableVehicle;
import com.vivek.rental.car.repository.UserRepository;
import com.vivek.rental.car.repository.VehicleRepository;

import java.time.Duration;
import java.util.UUID;

public class MonthInvoiceService implements InvoiceService {

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusMonths(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double months = Math.ceil(days / 30);

        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());

        double monthlyCost = VehicleMonthlyCosts.
                vehicleMonthlyCost.get(hireableVehicle.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double addonServiceCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        invoice.setAddonCost(vehicleAddonCost);
        invoice.setAddonServicesCost(addonServiceCost);
        double rentalCost = months * monthlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}
