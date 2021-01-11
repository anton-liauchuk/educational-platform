package com.educational.platform.administration.integration.event;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents course approved by admin integration event, should be published after approval the course by admin.
 */
@Getter
public class CourseApprovedByAdminIntegrationEvent {


    private final UUID courseId;


    /**
     * Create a new {@code CourseApprovedByAdminIntegrationEvent}.
     *
     * @param courseId course id
     */
    public CourseApprovedByAdminIntegrationEvent(UUID courseId) {
        this.courseId = courseId;
    }
}
