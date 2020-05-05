package com.educational.platform.courses.course;

import java.util.UUID;

/**
 * Course cannot be published exception, can be thrown when course is not approved in the case of publish action
 */
public class CourseCannotBePublishedException extends RuntimeException {

    private final UUID uuid;

    public CourseCannotBePublishedException(UUID uuid) {
        super();
        this.uuid = uuid;
    }

    @Override
    public String getMessage() {
        return "Course with uuid = " + uuid + " cannot be published, course should be approved by admin at first";
    }
}
