package com.educational.platform.course.enrollments;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Represents course enrollment factory.
 */
@RequiredArgsConstructor
@Component
public class CourseEnrollmentFactory {

    private final Validator validator;
    private final CourseRepository courseRepository;
    private final CurrentUserAsStudent currentUserAsStudent;

    /**
     * Creates course enrollment from command.
     *
     * @param command command.
     * @return course enrollment.
     * @throws ConstraintViolationException          in the case of validation issues
     * @throws RelatedResourceIsNotResolvedException if student or course is not found by id
     */
    public CourseEnrollment createFrom(RegisterStudentToCourseCommand command) {
        final Set<ConstraintViolation<RegisterStudentToCourseCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final Course course = courseRepository.findByUuid(command.getCourseId())
                .orElseThrow(() -> new RelatedResourceIsNotResolvedException("Course cannot be found by uuid = " + command.getCourseId()));

        final Student student = currentUserAsStudent.userAsStudent();

        return new CourseEnrollment(command, course, student);
    }
}
