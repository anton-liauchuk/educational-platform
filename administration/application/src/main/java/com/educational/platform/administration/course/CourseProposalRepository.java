package com.educational.platform.administration.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    /**
     * Retrieves list of course proposals.
     *
     * @return list of course proposals.
     */
    @Query("select new com.educational.platform.administration.course.CourseProposalDTO(cp.uuid, cp.status) from CourseProposal cp")
    List<CourseProposalDTO> listCourseProposals();

}
