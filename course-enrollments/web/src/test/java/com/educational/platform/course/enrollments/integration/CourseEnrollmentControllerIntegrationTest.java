package com.educational.platform.course.enrollments.integration;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.course.enrollments.CourseEnrollmentController;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Represents course enrollment controller integration tests.
 */
@WebMvcTest(CourseEnrollmentController.class)
public class CourseEnrollmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterStudentToCourseCommandHandler handler;

    @Test
    void enroll_validRequest_created() throws Exception {
        this.mockMvc.perform(post("/courses/{uuid}/course-enrollments", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"student\": \"username\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void enroll_relatedResourceIsNotResolvedException_badRequest() throws Exception {
        doThrow(RelatedResourceIsNotResolvedException.class).when(handler).handle(any(RegisterStudentToCourseCommand.class));

        this.mockMvc.perform(post("/courses/{uuid}/course-enrollments", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"student\": \"username\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void enroll_constraintViolationException_badRequest() throws Exception {
        final ConstraintViolationException exception = mock(ConstraintViolationException.class);
        doReturn(new HashSet<>()).when(exception).getConstraintViolations();
        doThrow(exception).when(handler).handle(any(RegisterStudentToCourseCommand.class));

        this.mockMvc.perform(post("/courses/{uuid}/course-enrollments", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"student\": \"username\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
