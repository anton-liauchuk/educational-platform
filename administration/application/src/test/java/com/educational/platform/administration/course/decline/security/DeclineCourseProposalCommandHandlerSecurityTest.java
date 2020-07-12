package com.educational.platform.administration.course.decline.security;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.CourseProposalStatus;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommand;
import com.educational.platform.administration.course.decline.DeclineCourseProposalCommandHandler;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class DeclineCourseProposalCommandHandlerSecurityTest {

    private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
    @Autowired
    private CourseProposalRepository courseProposalRepository;
    @SpyBean
    private DeclineCourseProposalCommandHandler sut;

    @BeforeEach
    void setUp() {
        final CourseProposal courseProposal = new CourseProposal(new CreateCourseProposalCommand(courseUuid));
        courseProposalRepository.save(courseProposal);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void handle_userIsAdmin_courseDeclined() {
        // given
        var command = new DeclineCourseProposalCommand(courseUuid);

        // when
        sut.handle(command);

        // then
        final Optional<CourseProposal> saved = courseProposalRepository.findByUuid(courseUuid);
        assertThat(saved.get()).hasFieldOrPropertyWithValue("status", CourseProposalStatus.DECLINED);
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void handle_userIsTeacher_accessDeniedException() {
        // given
        var command = new DeclineCourseProposalCommand(courseUuid);

        // when
        final ThrowingCallable declineAction = () -> sut.handle(command);

        // then
        assertThatThrownBy(declineAction)
                .hasRootCauseInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void handle_userIsStudent_accessDeniedException() {
        // given
        var command = new DeclineCourseProposalCommand(courseUuid);

        // when
        final ThrowingCallable declineAction = () -> sut.handle(command);

        // then
        assertThatThrownBy(declineAction)
                .hasRootCauseInstanceOf(AccessDeniedException.class);
    }
}
