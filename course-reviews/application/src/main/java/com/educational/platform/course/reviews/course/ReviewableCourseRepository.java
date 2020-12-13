package com.educational.platform.course.reviews.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.educational.platform.course.reviews.course.ReviewableCourse;

/**
 * Represents reviewable course repository.
 */
public interface ReviewableCourseRepository extends JpaRepository<ReviewableCourse, Integer> {

    /**
     * Retrieves a course by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<ReviewableCourse> findByOriginalCourseId(UUID uuid);

}
