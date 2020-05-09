package com.educational.platform.course.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course review repository.
 */
public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {

    /**
     * Retrieves a course review by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course review with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<CourseReview> findByUuid(UUID uuid);

}
