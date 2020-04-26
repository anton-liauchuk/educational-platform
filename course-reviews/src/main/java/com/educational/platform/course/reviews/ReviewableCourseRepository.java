package com.educational.platform.course.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents reviewable course repository.
 */
public interface ReviewableCourseRepository extends JpaRepository<ReviewableCourse, Integer> {
}
