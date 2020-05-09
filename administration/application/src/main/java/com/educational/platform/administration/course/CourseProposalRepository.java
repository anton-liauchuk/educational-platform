package com.educational.platform.administration.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course proposal repository.
 */
public interface CourseProposalRepository extends JpaRepository<CourseProposal, Integer> {

    /**
     * Retrieves a course proposal by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course proposal with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<CourseProposal> findByUuid(UUID uuid);

}
