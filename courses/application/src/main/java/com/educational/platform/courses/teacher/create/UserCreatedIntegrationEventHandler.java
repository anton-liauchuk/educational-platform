package com.educational.platform.courses.teacher.create;

import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link UserCreatedIntegrationEvent}.
 */
// todo should be transactional?
@Component
public class UserCreatedIntegrationEventHandler {

    private final CreateTeacherCommandHandler createTeacherCommandHandler;

    public UserCreatedIntegrationEventHandler(CreateTeacherCommandHandler createTeacherCommandHandler) {
        this.createTeacherCommandHandler = createTeacherCommandHandler;
    }

    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedIntegrationEvent event) {
        createTeacherCommandHandler.handle(new CreateTeacherCommand(event.username()));
    }

}
