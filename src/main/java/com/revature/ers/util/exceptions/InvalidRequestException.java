package com.revature.ers.util.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Incorrect request parameters.");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}