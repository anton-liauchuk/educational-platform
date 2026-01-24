package com.educational.platform.courses.teacher.create;

import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;

import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link UserCreatedIntegrationEvent}.
 */
// todo should be transactional?
@Component
public class UserCreatedIntegrationEventHandler {

    private final CommandGateway commandGateway;

    public UserCreatedIntegrationEventHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handleUserCreatedEvent(UserCreatedIntegrationEvent event) {
        commandGateway.send(new CreateTeacherCommand(event.username()));
    }

}
