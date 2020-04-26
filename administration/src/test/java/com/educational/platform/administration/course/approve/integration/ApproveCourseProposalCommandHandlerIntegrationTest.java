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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApproveCourseProposalCommandHandlerIntegrationTest {


    @Autowired
    private CourseProposalRepository repository;

    @SpyBean
    private ApproveCourseProposalCommandHandler sut;


    @Test
    void handle_existingCourseProposal_courseSavedWithStatusApproved() {
        // given
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(11);
        final CourseProposal existingCourseProposal = new CourseProposal(createCourseProposalCommand);
        repository.save(existingCourseProposal);

        final Integer id = (Integer) ReflectionTestUtils.getField(existingCourseProposal, "id");
        final ApproveCourseProposalCommand command = new ApproveCourseProposalCommand(id);

        // when
        sut.handle(command);

        // then
        assertThat(id).isNotNull();
        final Optional<CourseProposal> saved = repository.findById(id);
        assertThat(saved).isNotEmpty();
        final CourseProposal courseProposal = saved.get();
        assertThat(courseProposal).hasFieldOrPropertyWithValue("status", CourseProposalStatus.APPROVED);
    }
}
