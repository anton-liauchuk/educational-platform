package com.educational.platform.userservice.dto;

import com.educational.platform.userservice.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRegistrationDTO {

    private List<Role> roles;
    private String username;
    private String email;
    private String password;

}
