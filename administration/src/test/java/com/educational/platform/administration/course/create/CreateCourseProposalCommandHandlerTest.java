package com.educational.platform.administration.course.create;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.administration.course.create.CreateCourseProposalCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCourseProposalCommandHandlerTest {

    @Mock
    private CourseProposalRepository repository;

    @InjectMocks
    private CreateCourseProposalCommandHandler sut;

    @Test
    void handle_courseProposalSaved() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CreateCourseProposalCommand command = new CreateCourseProposalCommand(uuid);

        // when
        sut.handle(command);

        // then
        final ArgumentCaptor<CourseProposal> argument = ArgumentCaptor.forClass(CourseProposal.class);
        verify(repository).save(argument.capture());
        final CourseProposal proposal = argument.getValue();
        assertThat(proposal)
                .hasFieldOrPropertyWithValue("originalCourseId", uuid);
    }
}
