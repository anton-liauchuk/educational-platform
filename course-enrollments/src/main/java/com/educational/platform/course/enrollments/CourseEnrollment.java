package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents course enrollment domain model.
 */
@Entity
public class CourseEnrollment {

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    // for JPA
    private CourseEnrollment() {
    }

    public CourseEnrollment(RegisterStudentToCourseCommand command, Course course, Student student) {
        this.course = course;
        this.student = student;
    }
}
