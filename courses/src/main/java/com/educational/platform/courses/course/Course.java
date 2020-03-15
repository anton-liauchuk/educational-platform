package com.educational.platform.courses.course;

import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.teacher.Teacher;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

/**
 * Represents Course domain model.
 */
// todo mark as aggregate root
@Entity
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Status status;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private CourseRating rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    public Course(CreateCourseCommand command) {
        this.name = command.getName();
        this.description = command.getDescription();
    }

    public void approvedByAdmin() {
        status = Status.APPROVED;
    }

    public void updateRating(double value) {
//        rating = new CourseRating(value);
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
