package com.educational.platform.administration.course.create;

import com.educational.platform.courses.integration.event.SendCourseToApproveIntegrationEvent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link SendCourseToApproveIntegrationEvent}, executes the logic for creating course proposal by {@link CreateCourseProposalCommandHandler}.
 */
@Component
public class SendCourseToApproveIntegrationEventHandler {

    private final CreateCourseProposalCommandHandler createCourseProposalCommandHandler;

    public SendCourseToApproveIntegrationEventHandler(CreateCourseProposalCommandHandler createCourseProposalCommandHandler) {
        this.createCourseProposalCommandHandler = createCourseProposalCommandHandler;
    }

    @Async
    @EventListener
    public void handleSendCourseToApproveEvent(SendCourseToApproveIntegrationEvent event) {
        createCourseProposalCommandHandler.handle(new CreateCourseProposalCommand(event.courseId()));
    }

}
