package com.educational.platform.userservice.model.dto;

import com.educational.platform.userservice.model.Role;
import com.educational.platform.userservice.model.validator.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UserRegistrationDTO {

    @NotEmpty(message = "Roles are required")
    private List<Role> roles;

    @NotBlank(message = "Username is required")
    private String username;

    // todo check null
    @Email(message = "Email is incorrect")
    private String email;

    @ValidPassword
    private String password;

}
