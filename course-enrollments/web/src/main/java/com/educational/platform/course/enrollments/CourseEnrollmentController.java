package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.query.ListCourseEnrollmentsQuery;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Represents Course Enrollment Controller.
 */
@RestController
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping(value = "/courses/{uuid}/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID enroll(@PathVariable UUID uuid, @RequestBody CourseEnrollmentRequest request) {
        var command = RegisterStudentToCourseCommand.builder()
                .courseId(uuid)
                .build();

        return commandGateway.sendAndWait(command);
    }

    @GetMapping(value = "/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CourseEnrollmentDTO> courseEnrollments() {
        return queryGateway.query(new ListCourseEnrollmentsQuery(), ResponseTypes.multipleInstancesOf(CourseEnrollmentDTO.class)).join();
    }
}
