package com.educational.platform.course.reviews;

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

    /**
     * Creates course review from command.
     *
     * @param reviewCourseCommand review course command
     * @return course review
     * @throws ConstraintViolationException in the case of validation issues
     */
    public CourseReview createFrom(ReviewCourseCommand reviewCourseCommand) {
        final Set<ConstraintViolation<ReviewCourseCommand>> violations = validator.validate(reviewCourseCommand);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return new CourseReview(reviewCourseCommand);
    }

}
