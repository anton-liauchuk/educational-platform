package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course enrollment repository.
 */
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {

    /**
     * Retrieves a course enrollment by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course enrollment with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<CourseEnrollment> findByUuid(UUID uuid);

}
