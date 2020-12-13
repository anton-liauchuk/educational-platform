package com.educational.platform.course.reviews;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommand;
import com.educational.platform.course.reviews.reviewer.Reviewer;

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

    private Integer reviewer;
    private Integer course;
    private CourseRating rating;
    private Comment comment;

    // for JPA
    private CourseReview() {

    }

    CourseReview(ReviewCourseCommand command, Integer course, Integer reviewer) {
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
