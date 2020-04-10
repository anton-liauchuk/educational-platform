package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Represents user created integration event, should be published after user creation.
 */
@Getter
public class UserCreatedIntegrationEvent extends ApplicationEvent {


    private final String username;


    /**
     * Create a new {@code UserCreatedIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param username username
     */
    public UserCreatedIntegrationEvent(Object source, String username) {
        super(source);
        this.username = username;
    }
}
