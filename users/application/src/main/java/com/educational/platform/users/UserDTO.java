package com.educational.platform.users;

import lombok.Builder;

/**
 * Represents User DTO.
 */
@Builder
public record UserDTO(String username, String email, RoleDTO role) {

}
