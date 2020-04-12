package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Represents course approved by admin integration event, should be published after approval the course by admin.
 */
@Getter
public class CourseApprovedByAdminIntegrationEvent extends ApplicationEvent {


    private final int courseId;


    /**
     * Create a new {@code CourseApprovedByAdminIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     */
    public CourseApprovedByAdminIntegrationEvent(Object source, int courseId) {
        super(source);
        this.courseId = courseId;
    }
}
