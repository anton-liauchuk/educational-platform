package com.educational.platform.administration.course;

import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CourseProposalTest {

    @Test
    void create_validCommand_created() {
        // given
        final CreateCourseProposalCommand command = new CreateCourseProposalCommand(15);

        // when
        final CourseProposal courseProposal = new CourseProposal(command);

        // then
        assertThat(courseProposal)
                .hasFieldOrPropertyWithValue("originalCourseId", 15)
                .hasFieldOrPropertyWithValue("status", CourseProposalStatus.WAITING_FOR_APPROVAL);
    }

    @Test
    void approve_approvedStatus() {
        // given
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(15);
        final CourseProposal proposal = new CourseProposal(createCourseProposalCommand);

        // when
        proposal.approve();

        // then
        assertThat(proposal)
                .hasFieldOrPropertyWithValue("status", CourseProposalStatus.APPROVED);
    }

    @Test
    void approve_courseProposalAlreadyApproved_courseProposalAlreadyApprovedException() {
        // given
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(15);
        final CourseProposal proposal = new CourseProposal(createCourseProposalCommand);
        ReflectionTestUtils.setField(proposal, "id", 11);
        ReflectionTestUtils.setField(proposal, "status", CourseProposalStatus.APPROVED);

        // when
        final ThrowableAssert.ThrowingCallable sendToApprove = proposal::approve;

        // then
        assertThatExceptionOfType(CourseProposalAlreadyApprovedException.class).isThrownBy(sendToApprove);
    }

}
