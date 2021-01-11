package com.educational.platform.courses.integration.event;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents send course to approve integration event.
 */
@Getter
public class SendCourseToApproveIntegrationEvent {

    private final UUID courseId;


    /**
     * Create a new {@code SendCourseToApproveIntegrationEvent}.
     *
     * @param courseId course id
     */
    public SendCourseToApproveIntegrationEvent(UUID courseId) {
        this.courseId = courseId;
    }

}
