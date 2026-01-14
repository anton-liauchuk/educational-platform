package com.educational.platform.users;

/**
 * Represents User DTO.
 */
public record UserDTO(String username, String email, RoleDTO role) {

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public static final class UserDTOBuilder {
        private String username;
        private String email;
        private RoleDTO role;

        private UserDTOBuilder() {
        }

        public UserDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder role(RoleDTO role) {
            this.role = role;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(username, email, role);
        }
    }
}
