package com.educational.platform.courses.course.publish;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseCannotBePublishedException;
import com.educational.platform.courses.course.CourseRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link PublishCourseCommand} publishes a course.
 */
@Component
@Transactional
public class PublishCourseCommandHandler {

    private final CourseRepository repository;

    public PublishCourseCommandHandler(CourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles publish course command. Publishes and save published course
     *
     * @param command command
     * @throws ResourceNotFoundException        if resource not found
     * @throws CourseCannotBePublishedException if course is not approved
     */
    @PreAuthorize("hasRole('TEACHER') and @courseTeacherChecker.hasAccess(authentication, #c.uuid)")
    public void handle(@P("c") PublishCourseCommand command) {
        final Optional<Course> dbResult = repository.findByUuid(command.uuid());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with uuid: %s not found", command.uuid()));
        }

        final Course course = dbResult.get();
        course.publish();
        repository.save(course);
    }
}
