package com.educational.platform.courses.course.approve;

import com.educational.platform.administration.integration.event.CourseApprovedByAdminIntegrationEvent;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link CourseApprovedByAdminIntegrationEvent}.
 */
@Component
public class CourseApprovedByAdminIntegrationEventHandler {

    private final CommandGateway commandGateway;

    public CourseApprovedByAdminIntegrationEventHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handleCourseApprovedByAdminEvent(CourseApprovedByAdminIntegrationEvent event) {
        commandGateway.send(new ApproveCourseCommand(event.courseId()));
    }

}
