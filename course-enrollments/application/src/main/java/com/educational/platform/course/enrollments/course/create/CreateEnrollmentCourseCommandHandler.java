package com.educational.platform.course.enrollments.course.create;

import com.educational.platform.common.domain.CommandHandler;
import com.educational.platform.course.enrollments.EnrollCourse;
import com.educational.platform.course.enrollments.EnrollCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateCourseCommand} creates a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateEnrollmentCourseCommandHandler implements CommandHandler {

    private final EnrollCourseRepository courseRepository;

    /**
     * Creates course from command.
     *
     * @param command command
     */
    public void handle(CreateCourseCommand command) {
        final EnrollCourse course = new EnrollCourse(command);
        courseRepository.save(course);
    }
}
