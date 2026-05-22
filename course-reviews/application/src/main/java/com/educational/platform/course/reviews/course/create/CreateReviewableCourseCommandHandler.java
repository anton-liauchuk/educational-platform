package com.educational.platform.course.reviews.course.create;

import com.educational.platform.course.reviews.course.ReviewableCourse;
import com.educational.platform.course.reviews.course.ReviewableCourseRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateReviewableCourseCommand} creates a reviewable course.
 */
@Component
@Transactional
public class CreateReviewableCourseCommandHandler {

    private final ReviewableCourseRepository reviewableCourseRepository;

    public CreateReviewableCourseCommandHandler(ReviewableCourseRepository reviewableCourseRepository) {
        this.reviewableCourseRepository = reviewableCourseRepository;
    }

    /**
     * Creates reviewable course from command.
     *
     * @param command command
     */
    public void handle(CreateReviewableCourseCommand command) {
        final ReviewableCourse reviewableCourse = new ReviewableCourse(command);
        reviewableCourseRepository.save(reviewableCourse);
    }
}
