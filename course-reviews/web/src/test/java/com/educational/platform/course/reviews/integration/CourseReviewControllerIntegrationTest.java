package com.educational.platform.course.reviews.integration;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.course.reviews.CourseReviewController;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommandHandler;
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
 * Represents course review controller integration tests.
 */
@WebMvcTest(CourseReviewController.class)
public class CourseReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewCourseCommandHandler handler;

    @Test
    void review_validRequest_created() throws Exception {
        this.mockMvc.perform(post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"reviewer\": \"username\",\n" +
                        "  \"rating\": 3.2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void review_emptyReviewer_badRequest() throws Exception {
        this.mockMvc.perform(post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  " +
                        "\"rating\": 3.2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void review_invalidRating_badRequest() throws Exception {
        this.mockMvc.perform(post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  " +
                        "\"reviewer\": \"username\",\n" +
                        "  \"rating\": 6\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void review_relatedResourceIsNotResolvedException_badRequest() throws Exception {
        doThrow(RelatedResourceIsNotResolvedException.class).when(handler).handle(any(ReviewCourseCommand.class));

        this.mockMvc.perform(post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"reviewer\": \"username\",\n" +
                        "  \"rating\": 3.2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void review_constraintViolationException_badRequest() throws Exception {
        final ConstraintViolationException exception = mock(ConstraintViolationException.class);
        doReturn(new HashSet<>()).when(exception).getConstraintViolations();
        doThrow(exception).when(handler).handle(any(ReviewCourseCommand.class));

        this.mockMvc.perform(post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{\n" +
                        "  \"reviewer\": \"username\",\n" +
                        "  \"rating\": 3.2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
