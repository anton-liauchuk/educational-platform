package com.educational.platform.administration.course.create.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.administration.course.create.CreateCourseProposalCommandHandler;

@SpringBootTest
public class CreateCourseProposalCommandHandlerIntegrationTest {

    @Autowired
    private CourseProposalRepository repository;

    @MockitoSpyBean
    private CreateCourseProposalCommandHandler sut;

    @Test
    void handle_validCommand_courseProposalSavedWithStatusWaitingForApproval() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CreateCourseProposalCommand command = new CreateCourseProposalCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<CourseProposal> saved = repository.findOne(Example.of(new CourseProposal(command)));
        assertThat(saved).isNotEmpty();
        final CourseProposal proposal = saved.get();
        assertThat(proposal).hasFieldOrPropertyWithValue("uuid", uuid);
    }
}
