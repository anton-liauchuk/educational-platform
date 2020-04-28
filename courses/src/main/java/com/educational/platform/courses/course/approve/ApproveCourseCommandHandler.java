package com.educational.platform.courses.course.approve;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link ApproveCourseCommand} approves a course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class ApproveCourseCommandHandler {

    private final CourseRepository repository;

    /**
     * Handles approve course command. Approves and save approved course
     *
     * @param command command
     * @throws ResourceNotFoundException        if resource not found
     */
    public void handle(ApproveCourseCommand command) {
        final Optional<Course> dbResult = repository.findByUuid(command.getUuid());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with uuid: %s not found", command.getUuid()));
        }

        final Course course = dbResult.get();
        course.approve();
        repository.save(course);
    }
}
