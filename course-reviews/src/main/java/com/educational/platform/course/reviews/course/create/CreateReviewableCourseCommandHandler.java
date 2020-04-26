package com.educational.platform.course.reviews.course.create;

import com.educational.platform.course.reviews.ReviewableCourse;
import com.educational.platform.course.reviews.ReviewableCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateReviewableCourseCommand} creates a reviewable course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateReviewableCourseCommandHandler {

    private final ReviewableCourseRepository reviewableCourseRepository;

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
