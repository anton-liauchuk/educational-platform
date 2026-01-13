package com.educational.platform.users.integration.event;

/**
 * Represents user created integration event, should be published after user creation.
 */
public record UserCreatedIntegrationEvent(String username, String email) {

}
