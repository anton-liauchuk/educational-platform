package com.educational.platform.course.reviews.create;

import com.educational.platform.course.reviews.CourseReview;
import com.educational.platform.course.reviews.CourseReviewFactory;
import com.educational.platform.course.reviews.CourseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link ReviewCourseCommand} creates a course review.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class ReviewCourseCommandHandler {

    private final CourseReviewRepository courseReviewRepository;
    private final CourseReviewFactory courseReviewFactory;

    /**
     * Creates course review from command.
     *
     * @param command command
     */
    public void handle(ReviewCourseCommand command) {
        final CourseReview courseReview = courseReviewFactory.createFrom(command);
        courseReviewRepository.save(courseReview);
    }

}
