package com.educational.platform.course.enrollments.course;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Represents course domain model.
 */
@Entity(name = "enroll_course")
public class EnrollCourse implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    // for JPA
    private EnrollCourse() {

    }

    public EnrollCourse(CreateCourseCommand createCourseCommand) {
        this.uuid = createCourseCommand.getUuid();
    }

    public Integer getId() {
        return id;
    }

    public UUID toReference() {
        return uuid;
    }
}
