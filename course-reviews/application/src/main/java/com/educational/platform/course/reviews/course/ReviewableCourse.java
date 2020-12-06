package com.educational.platform.course.reviews.course;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
        this.originalCourseId = createReviewableCourseCommand.getUuid();
    }

    public Integer getId() {
        return id;
    }
}
