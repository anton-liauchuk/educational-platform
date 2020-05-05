package com.educational.platform.courses.course;

import java.util.UUID;

/**
 * Course cannot be sent for approve exception because course is already approved
 */
public class CourseAlreadyApprovedException extends RuntimeException {

    private final UUID uuid;

    public CourseAlreadyApprovedException(UUID uuid) {
        super();
        this.uuid = uuid;
    }

    @Override
    public String getMessage() {
        return "Course with uuid = " + uuid + " cannot be sent for approval, course was already approved";
    }
}
