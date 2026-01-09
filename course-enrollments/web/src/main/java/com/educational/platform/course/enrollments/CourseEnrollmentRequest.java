package com.educational.platform.course.enrollments;

import jakarta.validation.constraints.NotNull;

/**
 * Represents course enrollment request.
 */
public record CourseEnrollmentRequest(@NotNull String student) {

}
