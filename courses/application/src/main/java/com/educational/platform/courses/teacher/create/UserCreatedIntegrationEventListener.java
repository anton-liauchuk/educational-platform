package com.educational.platform.courses.teacher.create;

import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link UserCreatedIntegrationEvent}.
 */
// todo should be transactional?
@Component
@RequiredArgsConstructor
public class UserCreatedIntegrationEventListener {

    private final CommandGateway commandGateway;

    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedIntegrationEvent event) {
        commandGateway.send(new CreateTeacherCommand(event.getUsername()));
    }

}
