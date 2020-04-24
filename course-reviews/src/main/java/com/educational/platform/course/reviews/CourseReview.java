package com.educational.platform.course.reviews;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Course Review domain model.
 */
public class CourseReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private CourseRating rating;
    private Comment comment;

    // for JPA
    private CourseReview() {

    }

    public CourseReview(ReviewCourseCommand command) {
        this.courseId = command.getCourseId();
        this.studentId = command.getStudentId();
        this.rating = new CourseRating(command.getRating());
        this.comment = new Comment(command.getComment());
    }
}
