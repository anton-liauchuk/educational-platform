package com.educational.platform.administration.course;

/**
 * Course proposal already approved exception because course proposal is already approved.
 */
public class CourseProposalAlreadyApprovedException extends RuntimeException {

    private final int id;

    public CourseProposalAlreadyApprovedException(int id) {
        super();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Course Proposal with id = " + id + " cannot be approved, course proposal was already approved";
    }
}
