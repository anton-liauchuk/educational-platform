package com.educational.platform.users.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * Represents Sign In Command.
 */
@Builder
public record SignInCommand(@NotBlank String username, @NotBlank String password) {

}
