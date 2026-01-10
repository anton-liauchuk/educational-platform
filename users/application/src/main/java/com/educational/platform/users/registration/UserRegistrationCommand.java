package com.educational.platform.users.registration;

import com.educational.platform.users.RoleDTO;
import com.educational.platform.users.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Represents User Registration Command.
 */
@Builder
public record UserRegistrationCommand(@NotNull RoleDTO role, @Size(min = 4, max = 255) @NotBlank String username,
                                      @Email @NotBlank String email, @ValidPassword @NotBlank String password) {

}
