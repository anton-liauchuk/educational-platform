package com.educational.platform.users.security;

/**
 * Represents jwt token validation exception
 */
public class JwtTokenValidationException extends RuntimeException {

    public JwtTokenValidationException(String message) {
        super(message);
    }
}
