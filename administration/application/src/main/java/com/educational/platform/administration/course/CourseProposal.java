package com.educational.platform.administration.course;

import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.common.domain.AggregateRoot;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents Course Proposal domain model.
 */
@Entity
public class CourseProposal implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private CourseProposalStatus status;

    // for JPA
    private CourseProposal() {

    }

    public CourseProposal(CreateCourseProposalCommand command) {
        this.uuid = command.uuid();
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
        return new CourseProposalDTO(uuid, status.toDTO());
    }
}
