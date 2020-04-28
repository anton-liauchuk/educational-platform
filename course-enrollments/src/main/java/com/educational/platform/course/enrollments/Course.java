package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents course domain model.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer originalCourseId;

    // for JPA
    private Course() {

    }

    public Course(CreateCourseCommand createCourseCommand) {
        this.originalCourseId = createCourseCommand.getId();
    }

    public Integer getOriginalCourseId() {
        return originalCourseId;
    }
}
