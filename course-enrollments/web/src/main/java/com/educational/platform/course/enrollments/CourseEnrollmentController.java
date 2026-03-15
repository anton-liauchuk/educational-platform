package com.educational.platform.course.enrollments;

import com.educational.platform.course.enrollments.query.ListCourseEnrollmentsQuery;
import com.educational.platform.course.enrollments.query.ListCourseEnrollmentsQueryHandler;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;

import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;
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

    private final RegisterStudentToCourseCommandHandler registerStudentToCourseCommandHandler;
    private final ListCourseEnrollmentsQueryHandler listCourseEnrollmentsQueryHandler;

    public CourseEnrollmentController(RegisterStudentToCourseCommandHandler registerStudentToCourseCommandHandler, ListCourseEnrollmentsQueryHandler listCourseEnrollmentsQueryHandler) {
        this.registerStudentToCourseCommandHandler = registerStudentToCourseCommandHandler;
        this.listCourseEnrollmentsQueryHandler = listCourseEnrollmentsQueryHandler;
    }

    @PostMapping(value = "/courses/{uuid}/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID enroll(@PathVariable("uuid") UUID uuid, @RequestBody CourseEnrollmentRequest request) {
        return registerStudentToCourseCommandHandler.handle(new RegisterStudentToCourseCommand(uuid));
    }

    @GetMapping(value = "/course-enrollments", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CourseEnrollmentDTO> courseEnrollments() {
        return listCourseEnrollmentsQueryHandler.handle(new ListCourseEnrollmentsQuery());
    }
}
