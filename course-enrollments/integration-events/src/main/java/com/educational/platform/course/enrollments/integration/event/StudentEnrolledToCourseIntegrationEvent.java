package com.educational.platform.course.enrollments.integration.event;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents student enrolled to course integration event, should be published after enrollment to course by student.
 */
@Getter
public class StudentEnrolledToCourseIntegrationEvent {


    private final UUID courseId;
    private final String username;


    /**
     * Create a new {@code StudentEnrolledToCourseIntegrationEvent}.
     *
     * @param courseId course id
     * @param username student username
     */
    public StudentEnrolledToCourseIntegrationEvent(UUID courseId, String username) {
        this.courseId = courseId;
        this.username = username;
    }
}
