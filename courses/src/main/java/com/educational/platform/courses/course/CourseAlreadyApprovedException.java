package com.educational.platform.courses.course;

/**
 * Course cannot be sent for approve exception because course is already approved
 */
public class CourseAlreadyApprovedException extends RuntimeException {

    private final int id;

    public CourseAlreadyApprovedException(int id) {
        super();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Course with id = " + id + " cannot be sent for approval, course was already approved";
    }
}
