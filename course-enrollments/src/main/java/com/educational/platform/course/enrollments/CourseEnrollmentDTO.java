package com.educational.platform.course.enrollments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Represents DTO for {@link CourseEnrollment}
 */
@Builder
@Data
@AllArgsConstructor
public class CourseEnrollmentDTO {

    private final UUID uuid;
    private final UUID course;
    private final String student;

}
