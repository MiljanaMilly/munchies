package com.munchies.exceptions;

public class OrderNotExistsException extends Exception {
    public OrderNotExistsException() {
        super();
    }

    public OrderNotExistsException(String message) {
        super(message);
    }

    public OrderNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
