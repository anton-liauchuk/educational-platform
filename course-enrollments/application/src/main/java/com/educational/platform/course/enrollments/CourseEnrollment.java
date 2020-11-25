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

    private Integer course;

    private Integer student;

    private CompletionStatus completionStatus;

    // for JPA
    private CourseEnrollment() {
    }

    public CourseEnrollment(Integer course, Integer student) {
        this.uuid = UUID.randomUUID();
        this.course = course;
        this.student = student;
        this.completionStatus = CompletionStatus.IN_PROGRESS;
    }

    public void complete() {
        this.completionStatus = CompletionStatus.COMPLETED;
    }

    public UUID getUuid() {
        return uuid;
    }
}
