package com.educational.platform.courses.course.numberofsudents.update;

import com.educational.platform.course.enrollments.integration.event.StudentEnrolledToCourseIntegrationEvent;

import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link StudentEnrolledToCourseIntegrationEvent}.
 */
@Component
public class StudentEnrolledToCourseIntegrationEventHandler {

    private final CommandGateway commandGateway;

    public StudentEnrolledToCourseIntegrationEventHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handleStudentEnrolledToCourseEvent(StudentEnrolledToCourseIntegrationEvent event) {
        commandGateway.send(new IncreaseNumberOfStudentsCommand(event.courseId()));
    }

}
