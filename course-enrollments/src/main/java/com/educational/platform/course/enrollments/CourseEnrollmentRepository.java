package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents course enrollment repository.
 */
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {
}
