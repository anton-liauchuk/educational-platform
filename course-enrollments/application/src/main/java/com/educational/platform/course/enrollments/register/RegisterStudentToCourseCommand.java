package com.educational.platform.course.enrollments.register;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Represents register student to course command.
 */
public record RegisterStudentToCourseCommand(@NotNull UUID courseId) {

}
