package com.educational.platform.integration.events;

import org.springframework.context.ApplicationEvent;

/**
 * Represents user created integration event, should published after user creation.
 */
public class UserCreatedIntegrationEvent extends ApplicationEvent {


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UserCreatedIntegrationEvent(Object source) {
        super(source);
    }
}
