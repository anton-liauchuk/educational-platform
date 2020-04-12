package com.educational.platform.courses.course.approve;

import com.educational.platform.integration.events.CourseApprovedByAdminIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link CourseApprovedByAdminIntegrationEvent}.
 */
@Component
@RequiredArgsConstructor
public class CourseApprovedByAdminIntegrationEventListener {

    private final ApproveCourseCommandHandler handler;

    @Async
    @EventListener
    public void handleCourseApprovedByAdminEvent(CourseApprovedByAdminIntegrationEvent event) {
        handler.handle(new ApproveCourseCommand(event.getCourseId()));
    }

}
