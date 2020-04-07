package com.educational.platform.courses.course.create;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

/**
 * Command handler for {@link CreateCourseCommand} creates a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateCourseCommandHandler {

    private final CourseRepository courseRepository;
    private final CourseFactory courseFactory;

    /**
     * Creates course from command.
     *
     * @param command command
     * @throws ConstraintViolationException in the case of validation issues
     */
    public void handle(CreateCourseCommand command) {
        final Course course = courseFactory.createFrom(command);
        courseRepository.save(course);
    }

}
