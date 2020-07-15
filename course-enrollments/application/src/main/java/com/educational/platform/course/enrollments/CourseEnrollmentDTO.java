package com.educational.platform.course.enrollments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * Represents DTO for {@link CourseEnrollment}
 */
@Builder
@AllArgsConstructor
@Value
public class CourseEnrollmentDTO {

    UUID uuid;
    UUID course;
    String student;
    CompletionStatusDTO completionStatus;

	public CourseEnrollmentDTO(UUID uuid, UUID course, String student, CompletionStatus completionStatus) {
		this.uuid = uuid;
		this.course = course;
		this.student = student;
		this.completionStatus = completionStatus.toDTO();
	}
}
