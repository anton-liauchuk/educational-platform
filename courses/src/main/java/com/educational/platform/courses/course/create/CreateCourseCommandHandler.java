package com.educational.platform.courses.course.create;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Command handler for {@link CreateCourseCommand} creates a course.
 */
// todo should be transactional?
@RequiredArgsConstructor
@Component
public class CreateCourseCommandHandler {

    private final CourseRepository courseRepository;

    public void handle(CreateCourseCommand command) {
        courseRepository.save(new Course(command));
    }

}
