package com.educational.platform.administration.course;

import com.educational.platform.administration.course.create.CreateCourseProposalCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Course Proposal domain model.
 */
@Entity
public class CourseProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer originalCourseId;
    private CourseProposalStatus status;

    // for JPA
    private CourseProposal() {

    }

    public CourseProposal(CreateCourseProposalCommand command) {
        this.originalCourseId = command.getId();
        this.status = CourseProposalStatus.WAITING_FOR_APPROVAL;
    }

    public void approve() {
        if (status == CourseProposalStatus.APPROVED) {
            throw new CourseProposalAlreadyApprovedException(id);
        }
        this.status = CourseProposalStatus.APPROVED;
    }

    public Integer getOriginalCourseId() {
        return originalCourseId;
    }
}
