package com.educational.platform.administration.course;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Represents DTO for {@link CourseProposal}
 */
@Builder
@AllArgsConstructor
@Value
public class CourseProposalDTO {

	UUID uuid;
	CourseProposalStatusDTO status;

	public CourseProposalDTO(UUID uuid, CourseProposalStatus status) {
		this.uuid = uuid;
		this.status = status.toDTO();
	}
}
