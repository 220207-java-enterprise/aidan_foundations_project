package com.revature.ers.util.exceptions;

public class AuthenticationException extends ResourceNotFoundException {

    public AuthenticationException() {
        super("No user found using the provided credentials.");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
