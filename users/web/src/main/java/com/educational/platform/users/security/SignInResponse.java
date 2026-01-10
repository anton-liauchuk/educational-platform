package com.educational.platform.users.security;

import lombok.Builder;

/**
 * Represents the response after sign in operation.
 */
@Builder
public record SignInResponse(String token) {

}
