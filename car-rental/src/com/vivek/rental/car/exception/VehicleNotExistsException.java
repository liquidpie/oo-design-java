package com.vivek.rental.car.exception;

public class VehicleNotExistsException extends RuntimeException {
    public VehicleNotExistsException(String message) {
        super(message);
    }
}
