package com.educational.platform.course.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents course review repository.
 */
public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {
}
