package com.educational.platform.common.exception;

/**
 * Represents Resource Not Found Exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
