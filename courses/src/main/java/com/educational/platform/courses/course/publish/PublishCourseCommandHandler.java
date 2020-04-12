package com.educational.platform.courses.course.publish;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseCannotBePublishedException;
import com.educational.platform.courses.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link PublishCourseCommand} publishes a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class PublishCourseCommandHandler {

    private final CourseRepository repository;

    /**
     * Handles publish course command. Publishes and save published course
     *
     * @param command command
     * @throws ResourceNotFoundException        if resource not found
     * @throws CourseCannotBePublishedException if course is not approved
     */
    public void handle(PublishCourseCommand command) {
        final Optional<Course> dbResult = repository.findById(command.getId());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with id: %s not found", command.getId()));
        }

        final Course course = dbResult.get();
        course.publish();
        repository.save(course);
    }
}
