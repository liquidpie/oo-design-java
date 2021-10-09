package com.vivek.rental.car.exception;

public class InvalidVehicleIdException extends RuntimeException {
    public InvalidVehicleIdException(String message) {
        super(message);
    }
}
