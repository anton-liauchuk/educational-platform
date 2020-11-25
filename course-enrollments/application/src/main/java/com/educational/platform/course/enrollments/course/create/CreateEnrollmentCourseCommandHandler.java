package com.educational.platform.course.enrollments.course.create;

import com.educational.platform.course.enrollments.course.EnrollCourse;
import com.educational.platform.course.enrollments.course.EnrollCourseRepository;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateCourseCommand} creates a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateEnrollmentCourseCommandHandler {

    private final EnrollCourseRepository courseRepository;

    /**
     * Creates course from command.
     *
     * @param command command
     */
    @CommandHandler
    public void handle(CreateCourseCommand command) {
        final EnrollCourse course = new EnrollCourse(command);
        courseRepository.save(course);
    }
}
