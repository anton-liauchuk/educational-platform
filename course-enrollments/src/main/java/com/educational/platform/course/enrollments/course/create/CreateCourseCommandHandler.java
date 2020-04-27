package com.educational.platform.course.enrollments.course.create;

import com.educational.platform.course.enrollments.Course;
import com.educational.platform.course.enrollments.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateCourseCommand} creates a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateCourseCommandHandler {

    private final CourseRepository courseRepository;

    /**
     * Creates course from command.
     *
     * @param command command
     */
    public void handle(CreateCourseCommand command) {
        final Course course = new Course(command);
        courseRepository.save(course);
    }
}
