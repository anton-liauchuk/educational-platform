package com.educational.platform.course.enrollments.integration.event;

import java.util.UUID;

/**
 * Represents student enrolled to course integration event, should be published after enrollment to course by student.
 */
public record StudentEnrolledToCourseIntegrationEvent(UUID courseId, String username) {

}
