package com.vivek.amz.locker.exception;

public class LockerNotFoundException extends RuntimeException {
    public LockerNotFoundException(String message) {
        super(message);
    }
}
