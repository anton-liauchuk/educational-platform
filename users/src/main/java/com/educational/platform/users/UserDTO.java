package com.educational.platform.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents User DTO.
 */
@Builder
@Data
@AllArgsConstructor
public class UserDTO {

    private final String username;
    private final String email;
    private final RoleDTO role;

}
