package com.test.ticket_service.business;

public class FailedCreationException extends RuntimeException {

    public FailedCreationException(String message) {
        super(message);
    }
}
