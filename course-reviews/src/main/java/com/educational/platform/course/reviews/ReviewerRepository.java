package com.educational.platform.course.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents reviewer repository.
 */
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
}
