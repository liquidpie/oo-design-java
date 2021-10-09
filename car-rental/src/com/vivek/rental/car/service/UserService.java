package com.vivek.rental.car.service;

import com.vivek.rental.car.exception.InvalidVehicleIdException;
import com.vivek.rental.car.exception.ReservationNotFoundException;
import com.vivek.rental.car.exception.VehicleBookedException;
import com.vivek.rental.car.model.reservation.VehicleReservation;
import com.vivek.rental.car.model.vehicle.HireableVehicle;
import com.vivek.rental.car.model.vehicle.VehicleLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException;

    VehicleReservation remoteReserve(VehicleReservation vehicleReservation);

    VehicleReservation cancel(String reservationId);

    HireableVehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<HireableVehicle> getHiredVehicles(String userId);

    List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                           LocalDateTime endDate);
}
