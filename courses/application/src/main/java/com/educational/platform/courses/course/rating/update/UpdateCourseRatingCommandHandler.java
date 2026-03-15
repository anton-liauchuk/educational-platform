package com.educational.platform.courses.course.rating.update;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;

import org.axonframework.messaging.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link UpdateCourseRatingCommand} updates a rating course.
 */
@Component
@Transactional
public class UpdateCourseRatingCommandHandler {

    private final CourseRepository repository;

    public UpdateCourseRatingCommandHandler(CourseRepository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateCourseRatingCommand command) {
        final Optional<Course> dbResult = repository.findByUuid(command.uuid());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with uuid: %s not found", command.uuid()));
        }

        final Course course = dbResult.get();
        course.updateRating(command.rating());
        repository.save(course);
    }

}
