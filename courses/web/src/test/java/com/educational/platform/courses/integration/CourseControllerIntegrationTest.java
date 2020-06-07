package com.educational.platform.courses.integration;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.CourseController;
import com.educational.platform.courses.course.CourseCannotBePublishedException;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.create.CreateCourseCommandHandler;
import com.educational.platform.courses.course.publish.PublishCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Represents course controller integration tests.
 */
@WebMvcTest(controllers = CourseController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCourseCommandHandler createCourseCommandHandler;

    @MockBean
    private PublishCourseCommandHandler publishCourseCommandHandler;

    @Test
    void create_validRequest_created() throws Exception {
        this.mockMvc.perform(post("/courses")
                .content("{\n" +
                        "  \"name\": \"name\",\n" +
                        "  \"description\": \"description\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void create_emptyName_badRequest() throws Exception {
        this.mockMvc.perform(post("/courses")
                .content("{\n" +
                        "  " +
                        "\"description\": \"description\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_emptyDescription_badRequest() throws Exception {
        this.mockMvc.perform(post("/courses")
                .content("{\n" +
                        "  " +
                        "\"name\": \"name\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_constraintViolationException_badRequest() throws Exception {
        final ConstraintViolationException exception = mock(ConstraintViolationException.class);
        doReturn(new HashSet<>()).when(exception).getConstraintViolations();
        doThrow(exception).when(createCourseCommandHandler).handle(any(CreateCourseCommand.class));

        this.mockMvc.perform(post("/courses")
                .content("{\n" +
                        "  \"name\": \"name\",\n" +
                        "  \"description\": \"description\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void publish_alreadyApprovedCourse_noContent() throws Exception {
        this.mockMvc.perform(put("/courses/{uuid}/publish-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void publish_resourceNotFoundException_notFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(publishCourseCommandHandler).handle(any(PublishCourseCommand.class));

        this.mockMvc.perform(put("/courses/{uuid}/publish-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void publish_courseCannotBePublishedException_conflict() throws Exception {
        doThrow(CourseCannotBePublishedException.class).when(publishCourseCommandHandler).handle(any(PublishCourseCommand.class));

        this.mockMvc.perform(put("/courses/{uuid}/publish-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
