package com.educational.platform.administration.course.approve.security;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.CourseProposalStatus;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommand;
import com.educational.platform.administration.course.approve.ApproveCourseProposalCommandHandler;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class ApproveCourseProposalCommandHandlerSecurityTest {

    private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
    @Autowired
    private CourseProposalRepository courseProposalRepository;
    @SpyBean
    private ApproveCourseProposalCommandHandler sut;

    @BeforeEach
    void setUp() {
        final CourseProposal courseProposal = new CourseProposal(new CreateCourseProposalCommand(courseUuid));
        courseProposalRepository.save(courseProposal);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void handle_userIsAdmin_courseApproved() {
        // given
        var command = new ApproveCourseProposalCommand(courseUuid);

        // when
        sut.handle(command);

        // then
        final Optional<CourseProposal> saved = courseProposalRepository.findByUuid(courseUuid);
        assertThat(saved.get()).hasFieldOrPropertyWithValue("status", CourseProposalStatus.APPROVED);
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void handle_userIsTeacher_accessDeniedException() {
        // given
        var command = new ApproveCourseProposalCommand(courseUuid);

        // when
        final Executable publishAction = () -> sut.handle(command);

        // then
        assertThrows(AccessDeniedException.class, publishAction);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void handle_userIsStudent_accessDeniedException() {
        // given
        var command = new ApproveCourseProposalCommand(courseUuid);

        // when
        final Executable publishAction = () -> sut.handle(command);

        // then
        assertThrows(AccessDeniedException.class, publishAction);
    }
}
