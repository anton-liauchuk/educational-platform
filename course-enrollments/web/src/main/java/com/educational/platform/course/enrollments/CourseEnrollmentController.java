package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Enrollment Controller.
 */
@RestController
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final RegisterStudentToCourseCommandHandler handler;

    @PostMapping(value = "/courses/{uuid}/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID enroll(@PathVariable UUID uuid, @RequestBody CourseEnrollmentRequest request) {
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(uuid)
                .student(request.getStudent())
                .build();

        return handler.handle(command);
    }
}
