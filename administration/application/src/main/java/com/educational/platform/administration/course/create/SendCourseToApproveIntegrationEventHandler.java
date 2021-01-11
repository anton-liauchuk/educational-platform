package com.educational.platform.administration.course.create;

import com.educational.platform.courses.integration.event.SendCourseToApproveIntegrationEvent;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link SendCourseToApproveIntegrationEvent}, executes the logic for creating course proposal by {@link CreateCourseProposalCommandHandler}.
 */
@Component
@RequiredArgsConstructor
public class SendCourseToApproveIntegrationEventHandler {

    private final CommandGateway commandGateway;

    @EventHandler
    public void handleSendCourseToApproveEvent(SendCourseToApproveIntegrationEvent event) {
        commandGateway.send(new CreateCourseProposalCommand(event.getCourseId()));
    }

}
