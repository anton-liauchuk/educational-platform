package com.user.management.api.exception;

/**
 * Signals that an exception has occurred while loading or updating an object in data storage.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs an ResourceNotFoundException with error detail message.
     *
     * @param message error detail message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
