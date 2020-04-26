package com.educational.platform.course.reviews;

import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Reviewer domain model.
 */
@Entity
public class Reviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer originalStudentId;

    // for JPA
    private Reviewer() {
    }

    public Reviewer(CreateReviewerCommand createReviewerCommand) {
        this.originalStudentId = createReviewerCommand.getId();
    }
}
