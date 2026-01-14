package com.educational.platform.users.registration;

import com.educational.platform.users.RoleDTO;
import com.educational.platform.users.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents User Registration Command.
 */
public record UserRegistrationCommand(@NotNull RoleDTO role, @Size(min = 4, max = 255) @NotBlank String username,
                                      @Email @NotBlank String email, @ValidPassword @NotBlank String password) {

    public static UserRegistrationCommandBuilder builder() {
        return new UserRegistrationCommandBuilder();
    }

    public static final class UserRegistrationCommandBuilder {
        private RoleDTO role;
        private String username;
        private String email;
        private String password;

        private UserRegistrationCommandBuilder() {
        }

        public UserRegistrationCommandBuilder role(RoleDTO role) {
            this.role = role;
            return this;
        }

        public UserRegistrationCommandBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserRegistrationCommandBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRegistrationCommandBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRegistrationCommand build() {
            return new UserRegistrationCommand(role, username, email, password);
        }
    }
}
