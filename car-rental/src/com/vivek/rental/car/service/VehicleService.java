package com.vivek.rental.car.service;

import com.vivek.rental.car.exception.VehicleNotExistsException;
import com.vivek.rental.car.model.vehicle.HireableVehicle;

public interface VehicleService {
    HireableVehicle getVehicleById(String id);

    HireableVehicle getVehicleByQrCode(String qrCode);

    HireableVehicle addVehicle(HireableVehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleNotExistsException;
}
