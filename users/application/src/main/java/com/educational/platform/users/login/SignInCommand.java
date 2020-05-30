package com.educational.platform.users.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Represents Sign In Command.
 */
@Builder
@Data
@AllArgsConstructor
public class SignInCommand {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
