package com.educational.platform.course.reviews.edit;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.course.reviews.CourseReview;
import com.educational.platform.course.reviews.CourseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

/**
 * Command handler for {@link UpdateCourseReviewCommand} updates a course review.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class UpdateCourseReviewCommandHandler {

    private final Validator validator;
    private final CourseReviewRepository courseReviewRepository;

    /**
     * Updates course review by values from command.
     *
     * @param command command
     * @throws ResourceNotFoundException    course review not found
     * @throws ConstraintViolationException validation issues
     */
    public void handle(UpdateCourseReviewCommand command) {
        final Optional<CourseReview> dbResult = courseReviewRepository.findByUuid(command.getUuid());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course Review with uuid: %s not found", command.getUuid()));
        }

        // todo move to validator
        final Set<ConstraintViolation<UpdateCourseReviewCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final CourseReview review = dbResult.get();
        review.update(command);
        courseReviewRepository.save(review);
    }

}
