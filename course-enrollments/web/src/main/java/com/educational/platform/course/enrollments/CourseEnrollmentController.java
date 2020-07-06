package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.gateway.CommandGateway;
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

    private final CommandGateway commandGateway;

    @PostMapping(value = "/courses/{uuid}/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID enroll(@PathVariable UUID uuid, @RequestBody CourseEnrollmentRequest request) {
        var command = RegisterStudentToCourseCommand.builder()
                .courseId(uuid)
                .build();

        return commandGateway.sendAndWait(command);
    }
}
