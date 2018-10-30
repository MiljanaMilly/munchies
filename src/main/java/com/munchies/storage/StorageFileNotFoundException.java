package com.munchies.storage;

public class StorageFileNotFoundException extends Exception {

    public StorageFileNotFoundException() {
        super();
    }

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
