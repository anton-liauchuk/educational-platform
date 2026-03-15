package com.educational.platform.administration.course.create;

import com.educational.platform.courses.integration.event.SendCourseToApproveIntegrationEvent;

import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.eventhandling.annotation.EventHandler;
import jakarta.inject.Named;

/**
 * Event listener for {@link SendCourseToApproveIntegrationEvent}, executes the logic for creating course proposal by {@link CreateCourseProposalCommandHandler}.
 */
@Named
public class SendCourseToApproveIntegrationEventHandler {

    private final CommandGateway commandGateway;

    public SendCourseToApproveIntegrationEventHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handleSendCourseToApproveEvent(SendCourseToApproveIntegrationEvent event) {
        commandGateway.send(new CreateCourseProposalCommand(event.courseId()));
    }

}
