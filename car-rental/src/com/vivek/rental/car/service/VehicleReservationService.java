package com.vivek.rental.car.service;

import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.model.vehicle.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleReservationService {
    List<VehicleReservation> getReservations(VehicleType vehicleType);

    boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate);
}
