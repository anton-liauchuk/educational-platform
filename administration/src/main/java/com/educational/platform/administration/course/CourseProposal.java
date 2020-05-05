package com.educational.platform.administration.course;

import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.common.domain.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Represents Course Proposal domain model.
 */
@Entity
public class CourseProposal implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;
    private CourseProposalStatus status;

    // for JPA
    private CourseProposal() {

    }

    public CourseProposal(CreateCourseProposalCommand command) {
        this.uuid = command.getUuid();
        this.status = CourseProposalStatus.WAITING_FOR_APPROVAL;
    }

    public void approve() {
        if (status == CourseProposalStatus.APPROVED) {
            throw new CourseProposalAlreadyApprovedException(uuid);
        }
        this.status = CourseProposalStatus.APPROVED;
    }

    public void decline() {
        if (status == CourseProposalStatus.DECLINED) {
            throw new CourseProposalAlreadyDeclinedException(uuid);
        }
        this.status = CourseProposalStatus.DECLINED;
    }

    public CourseProposalDTO toDTO() {
        return CourseProposalDTO.builder()
                .uuid(uuid)
                .status(status.toDTO())
                .build();
    }
}
