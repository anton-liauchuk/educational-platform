package com.educational.platform.course.reviews.reviewer.create;

import com.educational.platform.course.reviews.Reviewer;
import com.educational.platform.course.reviews.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateReviewerCommand} creates a reviewer.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateReviewerCommandHandler {

    private final ReviewerRepository reviewerRepository;

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
