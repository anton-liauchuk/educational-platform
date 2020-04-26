package com.educational.platform.course.reviews;

import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Reviewable Course domain model.
 */
@Entity
public class ReviewableCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer originalCourseId;

    // for JPA
    private ReviewableCourse() {
    }

    public ReviewableCourse(CreateReviewableCourseCommand createReviewableCourseCommand) {
        this.originalCourseId = createReviewableCourseCommand.getId();
    }
}
