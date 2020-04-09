package com.educational.platform.courses.course.numberofsudents.update;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link UpdateNumberOfStudentsCommand} updates a rating course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class UpdateNumberOfStudentsCommandHandler {

    private final CourseRepository repository;

    public void handle(UpdateNumberOfStudentsCommand command) {
        final Optional<Course> dbResult = repository.findById(command.getId());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with id: %s not found", command.getId()));
        }

        final Course course = dbResult.get();
        course.updateNumberOfStudents(command.getNumber());
        repository.save(course);
    }

}
