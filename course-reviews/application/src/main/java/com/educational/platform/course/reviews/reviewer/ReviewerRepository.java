package com.educational.platform.course.reviews.reviewer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educational.platform.course.reviews.reviewer.Reviewer;

/**
 * Represents reviewer repository.
 */
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

    /**
     * Retrieves a reviewer by its username.
     *
     * @param username must not be {@literal null}.
     * @return the reviewer with the given username.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    Reviewer findByUsername(String username);


}
