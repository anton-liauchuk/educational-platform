package com.educational.platform.users.login;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents Sign In Command.
 */
public record SignInCommand(@NotBlank String username, @NotBlank String password) {

    public static SignInCommandBuilder builder() {
        return new SignInCommandBuilder();
    }

    public static final class SignInCommandBuilder {
        private String username;
        private String password;

        private SignInCommandBuilder() {
        }

        public SignInCommandBuilder username(String username) {
            this.username = username;
            return this;
        }

        public SignInCommandBuilder password(String password) {
            this.password = password;
            return this;
        }

        public SignInCommand build() {
            return new SignInCommand(username, password);
        }
    }
}
