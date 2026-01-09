package com.educational.platform.course.enrollments;

import java.util.UUID;

/**
 * Represents DTO for {@link CourseEnrollment}
 */
public record CourseEnrollmentDTO(UUID uuid, UUID course, String student, CompletionStatusDTO completionStatus) {

    public CourseEnrollmentDTO(UUID uuid, UUID course, String student, CompletionStatus completionStatus) {
        this(uuid, course, student, completionStatus.toDTO());
    }
}
