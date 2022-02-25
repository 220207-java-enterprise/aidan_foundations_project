package com.revature.ers.util.exceptions;

public class DataSourceException extends RuntimeException {

    public DataSourceException(Throwable cause) {
        super("An exception occurred when communicating with the database.", cause);
    }
}
