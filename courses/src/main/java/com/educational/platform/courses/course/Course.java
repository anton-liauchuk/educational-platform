package com.educational.platform.courses.course;

import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.teacher.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Represents Course domain model.
 */
// todo mark as aggregate root
@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Status status;
    private CourseRating rating;

    @OneToOne
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    // todo constructor for tests, review solution
    public Course() {
        this.rating = new CourseRating(0);
    }

    // todo validation
    public Course(CreateCourseCommand command) {
        this.name = command.getName();
        this.description = command.getDescription();
        this.rating = new CourseRating(0);
    }

    public void publish() {
        status = Status.PUBLISHED;
    }

    public void archive() {
        status = Status.ARCHIVED;
    }

    public void updateRating(double value) {
        rating = new CourseRating(value);
    }

    // todo implement
    public void addLecture() {

    }

    // todo implement
    public void removeLecture() {

    }


}
