package com.vivek.fantasy.cricket.exception;

public enum ErrorCode {

    TEAM_SIZE_REACHED("Team Size is Reached!!"),
    NOT_ENOUGH_BUDGET("User doesn't have enough budget!!"),
    TEAM_COMBINATION_VIOLATED("Team combination violated!!");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
