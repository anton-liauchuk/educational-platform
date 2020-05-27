package com.educational.platform.users.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.educational.platform.users.RoleDTO;

/**
 * Represents Course Create Request.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private RoleDTO role;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
