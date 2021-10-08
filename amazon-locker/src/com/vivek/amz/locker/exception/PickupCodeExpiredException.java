package com.vivek.amz.locker.exception;

public class PickupCodeExpiredException extends RuntimeException {

    public PickupCodeExpiredException(String message) {
        super(message);
    }
}
