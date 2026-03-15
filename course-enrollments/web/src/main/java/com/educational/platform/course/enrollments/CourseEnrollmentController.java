package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.query.ListCourseEnrollmentsQuery;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;

import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.queryhandling.gateway.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Enrollment Controller.
 */
@RestController
public class CourseEnrollmentController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CourseEnrollmentController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping(value = "/courses/{uuid}/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID enroll(@PathVariable("uuid") UUID uuid, @RequestBody CourseEnrollmentRequest request) {
        var command = new RegisterStudentToCourseCommand(uuid);

        return commandGateway.sendAndWait(command, UUID.class);
    }

    @GetMapping(value = "/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CourseEnrollmentDTO> courseEnrollments() {
        return queryGateway.queryMany(new ListCourseEnrollmentsQuery(), CourseEnrollmentDTO.class).join();
    }
}
