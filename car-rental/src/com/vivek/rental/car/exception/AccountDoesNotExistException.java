package com.vivek.rental.car.exception;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
