package com.educational.platform.users.integration.event;

import lombok.Value;

/**
 * Represents user created integration event, should be published after user creation.
 */
@Value
public class UserCreatedIntegrationEvent {

	String username, email;
}
