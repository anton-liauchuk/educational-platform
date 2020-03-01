package com.educational.platform.courses.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Represents Course domain model.
 */
// todo mark as aggregate root
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Status status;
    private Teacher teacher;
    private CourseRating rating;
    private List<Lecture> lectures;

    public void approvedByAdmin() {
        status = Status.APPROVED;
    }

    public void updateRating(double value) {
        rating = new CourseRating(value);
    }

    // todo implement
    public void archive() {

    }

    // todo implement
    public void addLecture() {

    }

    // todo implement
    public void removeLecture() {

    }


}
