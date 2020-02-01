package com.educational.platform.userservice.model.dto;

import com.educational.platform.userservice.model.Role;
import lombok.Data;

import java.util.List;

// todo review solution with builder
@Data
public class UserResponseDTO {

    private Integer id;
    private List<Role> roles;
    private String username;
    private String email;

}
