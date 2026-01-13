package com.educational.platform.courses.course;

import com.educational.platform.courses.course.create.CreateCourseCommand;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

/**
 * Represents Course Factory.
 */
@Component
public class CourseFactory {

    private final Validator validator;
    private final CurrentUserAsTeacher currentUserAsTeacher;

    public CourseFactory(Validator validator, CurrentUserAsTeacher currentUserAsTeacher) {
        this.validator = validator;
        this.currentUserAsTeacher = currentUserAsTeacher;
    }

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
