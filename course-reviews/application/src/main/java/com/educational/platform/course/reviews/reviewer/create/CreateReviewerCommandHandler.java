package com.educational.platform.course.reviews.reviewer.create;

import com.educational.platform.course.reviews.reviewer.Reviewer;
import com.educational.platform.course.reviews.reviewer.ReviewerRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateReviewerCommand} creates a reviewer.
 */
@Component
@Transactional
public class CreateReviewerCommandHandler {

    private final ReviewerRepository reviewerRepository;

    public CreateReviewerCommandHandler(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    /**
     * Creates reviewer from command.
     *
     * @param command command
     */
    public void handle(CreateReviewerCommand command) {
        final Reviewer reviewer = new Reviewer(command);
        reviewerRepository.save(reviewer);
    }
}
