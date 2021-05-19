package com.vivek.mqs.exception;

public enum ErrorType {

    QUEUE_ALREADY_EXISTS,
    QUEUE_NOT_FOUND,
    PUBLISHER_ALREADY_EXISTS_FOR_QUEUE,
    PUBLISHER_NOT_FOUND,
    SUBSCRIBER_NOT_FOUND,
    EXISTING_SUBSCRIPTION_FOUND

}
