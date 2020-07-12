package com.educational.platform.course.reviews.edit.security;

import com.educational.platform.course.reviews.CourseReview;
import com.educational.platform.course.reviews.CourseReviewRepository;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommand;
import com.educational.platform.course.reviews.edit.UpdateCourseReviewCommandHandler;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql(scripts = "classpath:course_review.sql")
@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class UpdateCourseReviewCommandHandlerSecurityTest {

    private final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

    @Autowired
    private CourseReviewRepository repository;

    @SpyBean
    private UpdateCourseReviewCommandHandler sut;

    @Test
    @WithMockUser(username = "reviewer", roles = "STUDENT")
    void handle_userIsReviewer_courseReviewUpdated() {
        // given
        var command = UpdateCourseReviewCommand.builder()
                .uuid(uuid)
                .rating(3.0)
                .comment("updated comment")
                .build();

        // when
        sut.handle(command);

        // then
        final Optional<CourseReview> saved = repository.findByUuid(uuid);
        assertThat(saved.get()).hasFieldOrPropertyWithValue("uuid", uuid);
    }

    @Test
    @WithMockUser(username = "another_reviewer", roles = "STUDENT")
    void handle_anotherReviewer_accessDeniedException() {
        // given
        var command = UpdateCourseReviewCommand.builder()
                .uuid(uuid)
                .rating(3.0)
                .comment("updated comment")
                .build();

        // when
        final ThrowingCallable updateAction = () -> sut.handle(command);

        // then
        assertThatThrownBy(updateAction)
                .hasRootCauseInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void handle_userIsTeacher_accessDeniedException() {
        // given
        var command = UpdateCourseReviewCommand.builder()
                .uuid(uuid)
                .rating(3.0)
                .comment("updated comment")
                .build();

        // when
        final ThrowingCallable updateAction = () -> sut.handle(command);

        // then
        assertThatThrownBy(updateAction)
                .hasRootCauseInstanceOf(AccessDeniedException.class);
    }
}
