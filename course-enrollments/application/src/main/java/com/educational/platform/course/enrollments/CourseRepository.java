package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course repository.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

    /**
     * Retrieves a course by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<Course> findByUuid(UUID uuid);

}
