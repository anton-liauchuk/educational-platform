package com.educational.platform.users.registration;

import com.educational.platform.users.RoleDTO;
import com.educational.platform.users.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents User Registration Command.
 */
@Builder
@Data
@AllArgsConstructor
public class UserRegistrationCommand {

    @NotNull
    private RoleDTO role;

    @Size(min = 4, max = 255)
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @ValidPassword
    @NotBlank
    private String password;

}
