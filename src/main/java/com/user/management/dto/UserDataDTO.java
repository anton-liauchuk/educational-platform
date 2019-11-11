package com.user.management.dto;


import com.user.management.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {

    private String username;
    private String email;
    private String password;
    private List<Role> roles;

}
