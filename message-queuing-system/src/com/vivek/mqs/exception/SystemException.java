package com.vivek.mqs.exception;

public class SystemException extends RuntimeException {

    private final String message;

    public SystemException(ErrorType errorType) {
        super(errorType.name());
        this.message = errorType.name();
    }

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
