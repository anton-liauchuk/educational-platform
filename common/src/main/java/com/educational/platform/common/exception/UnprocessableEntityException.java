package com.educational.platform.common.exception;

/**
 * Represents Unprocessable Entity Exception.
 */
public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
