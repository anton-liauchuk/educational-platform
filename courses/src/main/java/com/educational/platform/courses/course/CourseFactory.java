package com.educational.platform.courses.course;

import com.educational.platform.courses.course.create.CreateCourseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

/**
 * Represents Course Factory.
 */
@RequiredArgsConstructor
@Component
public class CourseFactory {

    private final Validator validator;

    public Course createFrom(CreateCourseCommand courseCommand) {
        validator.validate(courseCommand);

        return new Course(courseCommand);
    }

}
