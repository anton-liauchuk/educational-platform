package com.educational.platform.course.reviews.course;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

/**
 * Represents Reviewable Course domain model.
 */
@Entity
public class ReviewableCourse implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID originalCourseId;

    // for JPA
    private ReviewableCourse() {
    }

    public ReviewableCourse(CreateReviewableCourseCommand createReviewableCourseCommand) {
        this.originalCourseId = createReviewableCourseCommand.uuid();
    }

    public Integer getId() {
        return id;
    }
}
