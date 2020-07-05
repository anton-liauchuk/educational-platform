package com.educational.platform.course.enrollments;

import com.educational.platform.common.domain.AggregateRoot;

import javax.persistence.*;
import java.util.UUID;

/**
 * Represents course enrollment domain model.
 */
@Entity
public class CourseEnrollment implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @ManyToOne
    private EnrollCourse course;

    @ManyToOne
    private Student student;

    private CompletionStatus completionStatus;

    // for JPA
    private CourseEnrollment() {
    }

    public CourseEnrollment(EnrollCourse course, Student student) {
        this.uuid = UUID.randomUUID();
        this.course = course;
        this.student = student;
        this.completionStatus = CompletionStatus.IN_PROGRESS;
    }

    public void complete() {
        this.completionStatus = CompletionStatus.COMPLETED;
    }

    public CourseEnrollmentDTO toDTO() {
        return CourseEnrollmentDTO.builder()
                .uuid(uuid)
                .course(course.toReference())
                .student(student.toReference())
                .completionStatus(completionStatus.toDTO())
                .build();
    }
}
