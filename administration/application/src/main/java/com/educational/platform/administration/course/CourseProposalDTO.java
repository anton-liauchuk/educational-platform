package com.educational.platform.administration.course;

import java.util.UUID;

/**
 * Represents DTO for {@link CourseProposal}
 */
public record CourseProposalDTO(UUID uuid, CourseProposalStatusDTO status) {

    public CourseProposalDTO(UUID uuid, CourseProposalStatus status) {
        this(uuid, status.toDTO());
    }
}
