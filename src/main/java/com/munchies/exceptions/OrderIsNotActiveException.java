package com.munchies.exceptions;

public class OrderIsNotActiveException extends Exception {
    public OrderIsNotActiveException() {
        super();
    }

    public OrderIsNotActiveException(String message) {
        super(message);
    }

    public OrderIsNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
