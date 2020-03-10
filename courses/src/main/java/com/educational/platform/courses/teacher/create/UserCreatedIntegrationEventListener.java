package com.educational.platform.courses.teacher.create;

import com.educational.platform.integration.events.UserCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link UserCreatedIntegrationEvent}.
 */
@Component
@RequiredArgsConstructor
public class UserCreatedIntegrationEventListener {

    private final CreateTeacherCommandHandler handler;

    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedIntegrationEvent event) {
        handler.handle(new CreateTeacherCommand(event.getUsername()));
    }

}
