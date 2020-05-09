package com.educational.platform.administration.course;

/**
 * Represents possible values for course proposal status
 */
public enum CourseProposalStatus {

    WAITING_FOR_APPROVAL,
    DECLINED,
    APPROVED;

    public CourseProposalStatusDTO toDTO() {
        switch (this) {
            case WAITING_FOR_APPROVAL:
                return CourseProposalStatusDTO.WAITING_FOR_APPROVAL;
            case DECLINED:
                return CourseProposalStatusDTO.DECLINED;
            case APPROVED:
                return CourseProposalStatusDTO.APPROVED;
        }

        return null;
    }

}
