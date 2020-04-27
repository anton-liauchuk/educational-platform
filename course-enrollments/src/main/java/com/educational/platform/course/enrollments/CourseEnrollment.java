package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;

import javax.persistence.*;

/**
 * Represents course enrollment domain model.
 */
@Entity
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
