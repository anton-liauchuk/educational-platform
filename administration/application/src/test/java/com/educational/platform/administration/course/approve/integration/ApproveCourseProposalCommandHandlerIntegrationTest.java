package com.educational.platform.administration.course.approve.integration;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.CourseProposalStatus;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommandHandler;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApproveCourseProposalCommandHandlerIntegrationTest {

    @Autowired
    private CourseProposalRepository repository;

    @SpyBean
    private ApproveCourseProposalCommandHandler sut;

    @Test
    void handle_existingCourseProposal_courseProposalSavedWithStatusApproved() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(uuid);
        final CourseProposal existingCourseProposal = new CourseProposal(createCourseProposalCommand);
        repository.save(existingCourseProposal);

        final ApproveCourseProposalCommand command = new ApproveCourseProposalCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<CourseProposal> saved = repository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
        final CourseProposal courseProposal = saved.get();
        assertThat(courseProposal).hasFieldOrPropertyWithValue("status", CourseProposalStatus.APPROVED);
    }
}
