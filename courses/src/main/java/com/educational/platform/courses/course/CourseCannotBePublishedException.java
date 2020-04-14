package com.educational.platform.courses.course;

/**
 * Course cannot be published exception, can be thrown when course is not approved in the case of publish action
 */
public class CourseCannotBePublishedException extends RuntimeException {

    private final int id;

    public CourseCannotBePublishedException(int id) {
        super();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Course with id = " + id + " cannot be published, course should be approved by admin at first";
    }
}
