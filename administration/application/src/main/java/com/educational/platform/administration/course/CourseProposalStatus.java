package com.educational.platform.administration.course;

/**
 * Represents possible values for course proposal status
 */
public enum CourseProposalStatus {

    WAITING_FOR_APPROVAL,
    DECLINED,
    APPROVED;

    public CourseProposalStatusDTO toDTO() {
		return switch (this) {
			case WAITING_FOR_APPROVAL -> CourseProposalStatusDTO.WAITING_FOR_APPROVAL;
			case DECLINED -> CourseProposalStatusDTO.DECLINED;
			case APPROVED -> CourseProposalStatusDTO.APPROVED;
		};
	}

}
