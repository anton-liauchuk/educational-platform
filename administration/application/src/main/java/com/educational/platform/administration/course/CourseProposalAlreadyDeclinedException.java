package com.educational.platform.administration.course;

import java.util.UUID;

/**
 * Represents course proposal already declined exception.
 */
public class CourseProposalAlreadyDeclinedException extends RuntimeException {

    private final UUID uuid;

    public CourseProposalAlreadyDeclinedException(UUID uuid) {
        super();
        this.uuid = uuid;
    }

    @Override
    public String getMessage() {
        return "Course Proposal with uuid = " + uuid + " cannot be declined, course proposal was already declined";
    }

}
