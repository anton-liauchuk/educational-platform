package com.educational.platform.course.reviews.reviewer;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents Reviewer domain model.
 */
@Entity
public class Reviewer implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    // for JPA
    private Reviewer() {
    }

    public Reviewer(CreateReviewerCommand createReviewerCommand) {
        this.username = createReviewerCommand.username();
    }

    public Integer getId() {
        return id;
    }
}
