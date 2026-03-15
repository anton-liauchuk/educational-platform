package com.educational.platform.administration.course.create;

import com.educational.platform.courses.integration.event.SendCourseToApproveIntegrationEvent;

import org.axonframework.messaging.eventhandling.annotation.EventHandler;
import jakarta.inject.Named;

/**
 * Event listener for {@link SendCourseToApproveIntegrationEvent}, executes the logic for creating course proposal by {@link CreateCourseProposalCommandHandler}.
 */
@Named
public class SendCourseToApproveIntegrationEventHandler {

    private final CreateCourseProposalCommandHandler createCourseProposalCommandHandler;

    public SendCourseToApproveIntegrationEventHandler(CreateCourseProposalCommandHandler createCourseProposalCommandHandler) {
        this.createCourseProposalCommandHandler = createCourseProposalCommandHandler;
    }

    @EventHandler
    public void handleSendCourseToApproveEvent(SendCourseToApproveIntegrationEvent event) {
        createCourseProposalCommandHandler.handle(new CreateCourseProposalCommand(event.courseId()));
    }

}
