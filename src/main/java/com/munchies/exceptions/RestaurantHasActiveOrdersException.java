package com.munchies.exceptions;

public class RestaurantHasActiveOrdersException extends Exception {

    public RestaurantHasActiveOrdersException() {
        super();
    }

    public RestaurantHasActiveOrdersException(String message) {
        super(message);
    }

    public RestaurantHasActiveOrdersException(String message, Throwable cause) {
        super(message, cause);
    }
}
