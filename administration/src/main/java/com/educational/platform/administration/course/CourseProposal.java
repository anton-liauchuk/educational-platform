package com.educational.platform.administration.course;

import com.educational.platform.administration.course.create.CreateCourseProposalCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Represents Course Proposal domain model.
 */
@Entity
public class CourseProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID originalCourseId;
    private CourseProposalStatus status;

    // for JPA
    private CourseProposal() {

    }

    public CourseProposal(CreateCourseProposalCommand command) {
        this.originalCourseId = command.getUuid();
        this.status = CourseProposalStatus.WAITING_FOR_APPROVAL;
    }

    public void approve() {
        if (status == CourseProposalStatus.APPROVED) {
            throw new CourseProposalAlreadyApprovedException(originalCourseId);
        }
        this.status = CourseProposalStatus.APPROVED;
    }

    public UUID getOriginalCourseId() {
        return originalCourseId;
    }
}
