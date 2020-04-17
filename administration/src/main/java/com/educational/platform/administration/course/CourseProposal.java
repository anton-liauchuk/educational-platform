package com.educational.platform.administration.course;

import com.educational.platform.administration.course.approve.CreateCourseProposalCommand;

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

    // for JPA
    private CourseProposal() {

    }

    public CourseProposal(CreateCourseProposalCommand command) {
        this.originalCourseId = command.getId();
    }
}
