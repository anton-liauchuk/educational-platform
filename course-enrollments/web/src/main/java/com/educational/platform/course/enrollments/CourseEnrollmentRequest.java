package com.educational.platform.course.enrollments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Represents course enrollment request.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollmentRequest {

    // todo get student from current user
    @NotNull
    private String student;

}
