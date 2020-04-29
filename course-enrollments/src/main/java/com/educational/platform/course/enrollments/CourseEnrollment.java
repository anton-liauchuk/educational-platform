package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;

import javax.persistence.*;
import java.util.UUID;

/**
 * Represents course enrollment domain model.
 */
@Entity
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    // for JPA
    private CourseEnrollment() {
    }

    public CourseEnrollment(RegisterStudentToCourseCommand command, Course course, Student student) {
        this.uuid = UUID.randomUUID();
        this.course = course;
        this.student = student;
    }

    public CourseEnrollmentDTO toDTO() {
        return CourseEnrollmentDTO.builder()
                .uuid(uuid)
                .course(course.getUuid())
                .student(student.getUsername())
                .build();
    }
}
