package com.revature.ers.util.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Incorrect request parameters received.");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
