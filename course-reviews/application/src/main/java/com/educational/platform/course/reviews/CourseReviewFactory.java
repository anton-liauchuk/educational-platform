package com.educational.platform.course.reviews;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Represents Course Review Factory.
 */
@RequiredArgsConstructor
@Component
public class CourseReviewFactory {

    private final Validator validator;
    private final ReviewableCourseRepository reviewableCourseRepository;
    private final ReviewerRepository reviewerRepository;

    /**
     * Creates course review from command.
     *
     * @param reviewCourseCommand review course command
     * @return course review
     * @throws ConstraintViolationException          in the case of validation issues
     * @throws RelatedResourceIsNotResolvedException if course or reviewer is not found by relation
     */
    public CourseReview createFrom(ReviewCourseCommand reviewCourseCommand) {
        final Set<ConstraintViolation<ReviewCourseCommand>> violations = validator.validate(reviewCourseCommand);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final ReviewableCourse course = reviewableCourseRepository.findByOriginalCourseId(reviewCourseCommand.getCourseId())
                .orElseThrow(() -> new RelatedResourceIsNotResolvedException("Course cannot be found by uuid = " + reviewCourseCommand.getCourseId()));

        final Reviewer reviewer = reviewerRepository.findByUsername(reviewCourseCommand.getReviewer())
                .orElseThrow(() -> new RelatedResourceIsNotResolvedException("Reviewer cannot be found by username = " + reviewCourseCommand.getReviewer()));

        return new CourseReview(reviewCourseCommand, course, reviewer);
    }

}
