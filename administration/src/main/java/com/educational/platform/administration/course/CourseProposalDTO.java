package com.educational.platform.administration.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Represents DTO for {@link CourseProposal}
 */
@Builder
@Data
@AllArgsConstructor
public class CourseProposalDTO {

    private final UUID uuid;

}
