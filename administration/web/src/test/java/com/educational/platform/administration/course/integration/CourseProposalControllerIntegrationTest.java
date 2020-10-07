package com.educational.platform.administration.course.integration;

import com.educational.platform.administration.course.CourseProposalAlreadyApprovedException;
import com.educational.platform.administration.course.CourseProposalAlreadyDeclinedException;
import com.educational.platform.administration.course.CourseProposalController;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommand;
import com.educational.platform.common.exception.ResourceNotFoundException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Represents course proposal controller integration tests.
 */
@WebMvcTest(controllers = CourseProposalController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CourseProposalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    void approve_existingCourseProposal_noContent() throws Exception {
        this.mockMvc.perform(put("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void approve_resourceNotFoundException_notFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(commandGateway).sendAndWait(any(ApproveCourseProposalCommand.class));

        this.mockMvc.perform(put("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void approve_courseProposalAlreadyApprovedException_conflict() throws Exception {
        doThrow(CourseProposalAlreadyApprovedException.class).when(commandGateway).sendAndWait(any(ApproveCourseProposalCommand.class));

        this.mockMvc.perform(put("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void decline_existingCourseProposal_noContent() throws Exception {
        this.mockMvc.perform(delete("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void decline_resourceNotFoundException_notFound() throws Exception {
        doThrow(ResourceNotFoundException.class).when(commandGateway).sendAndWait(any(DeclineCourseProposalCommand.class));

        this.mockMvc.perform(delete("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void decline_courseProposalAlreadyDeclinedException_conflict() throws Exception {
        doThrow(CourseProposalAlreadyDeclinedException.class).when(commandGateway).sendAndWait(any(DeclineCourseProposalCommand.class));

        this.mockMvc.perform(delete("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
