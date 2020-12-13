package com.educational.platform.courses.course;

import com.educational.platform.courses.course.create.CreateCourseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Represents Course Factory.
 */
@RequiredArgsConstructor
@Component
public class CourseFactory {

    private final Validator validator;
    private final CurrentUserAsTeacher currentUserAsTeacher;

    /**
     * Creates course from command.
     *
     * @param courseCommand course command
     * @return course
     * @throws ConstraintViolationException in the case of validation issues
     */
    public Course createFrom(CreateCourseCommand courseCommand) {
        final Set<ConstraintViolation<CreateCourseCommand>> violations = validator.validate(courseCommand);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        var teacher = currentUserAsTeacher.userAsTeacher();
        return new Course(courseCommand, teacher.getId());
    }

}
