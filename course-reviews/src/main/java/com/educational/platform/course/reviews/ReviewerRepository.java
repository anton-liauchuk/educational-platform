package com.educational.platform.course.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Represents reviewer repository.
 */
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

    /**
     * Retrieves a reviewer by its username.
     *
     * @param username must not be {@literal null}.
     * @return the reviewer with the given username or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    Optional<Reviewer> findByUsername(String username);


}
