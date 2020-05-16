package com.educational.platform.course.reviews;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommand;

import javax.persistence.*;
import java.util.UUID;

/**
 * Represents Course Review domain model.
 */
@Entity
public class CourseReview implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @ManyToOne
    private Reviewer reviewer;

    @ManyToOne
    private ReviewableCourse course;
    private CourseRating rating;
    private Comment comment;

    // for JPA
    private CourseReview() {

    }

    CourseReview(ReviewCourseCommand command, ReviewableCourse course, Reviewer reviewer) {
        this.uuid = UUID.randomUUID();
        this.course = course;
        this.reviewer = reviewer;
        this.rating = new CourseRating(command.getRating());
        this.comment = new Comment(command.getComment());
    }

    public void update(UpdateCourseReviewCommand command) {
        this.rating = new CourseRating(command.getRating());
        this.comment = new Comment(command.getComment());
    }

    public UUID toIdentifier() {
        return this.uuid;
    }
}
