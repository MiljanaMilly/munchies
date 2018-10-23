package com.munchies.exceptions;

public class RestaurantExistsException extends Exception {

    public RestaurantExistsException() {
        super();
    }

    public RestaurantExistsException(String message) {
        super(message);
    }

    public RestaurantExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
