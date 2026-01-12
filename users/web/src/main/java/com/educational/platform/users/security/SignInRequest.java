package com.educational.platform.users.security;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents Course Create Request.
 */
public record SignInRequest(@NotBlank String username, @NotBlank String password) {

}
