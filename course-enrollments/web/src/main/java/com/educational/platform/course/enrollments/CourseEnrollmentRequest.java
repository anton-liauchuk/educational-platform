package com.educational.platform.course.enrollments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Represents course enrollment request.
 */
@Builder
@Data
@AllArgsConstructor
public class CourseEnrollmentRequest {

    @NotNull
    private final String student;

}
