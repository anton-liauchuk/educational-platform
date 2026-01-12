package com.educational.platform.users.security;

import com.educational.platform.users.RoleDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents Course Create Request.
 */
public record SignUpRequest(@NotNull RoleDTO role, @NotBlank String username, @NotBlank String email,
                            @NotBlank String password) {

}
