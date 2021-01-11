package com.educational.platform.administration.integration.event;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents course declined by admin integration event.
 */
@Getter
public class CourseDeclinedByAdminIntegrationEvent {


    private final UUID courseId;


    /**
     * Create a new {@code CourseDeclinedByAdminIntegrationEvent}.
     *
     * @param courseId course id
     */
    public CourseDeclinedByAdminIntegrationEvent(UUID courseId) {
        this.courseId = courseId;
    }
}
