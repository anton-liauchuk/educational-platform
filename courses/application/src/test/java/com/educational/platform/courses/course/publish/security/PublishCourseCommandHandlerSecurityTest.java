package com.educational.platform.courses.course.publish.security;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.PublishStatus;
import com.educational.platform.courses.course.publish.PublishCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommandHandler;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@Sql(scripts = "classpath:approved_course.sql")
@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class PublishCourseCommandHandlerSecurityTest {

    private final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @SpyBean
    private PublishCourseCommandHandler sut;

    @Test
    @WithMockUser(username = "teacher", roles = "TEACHER")
    void handle_userIsTeacher_coursePublished() {
        // given
        final PublishCourseCommand command = new PublishCourseCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findByUuid(uuid);
        assertThat(saved.get()).hasFieldOrPropertyWithValue("publishStatus", PublishStatus.PUBLISHED);
    }

    @Test
    @WithMockUser(username = "another_teacher", roles = "TEACHER")
    void handle_anotherTeacher_accessDeniedException() {
        // given
        final PublishCourseCommand command = new PublishCourseCommand(uuid);

        // when
        final Executable publishAction = () -> sut.handle(command);

        // then
        assertThrows(AccessDeniedException.class, publishAction);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void handle_userIsStudent_accessDeniedException() {
        // given
        final PublishCourseCommand command = new PublishCourseCommand(uuid);

        // when
        final Executable publishAction = () -> sut.handle(command);

        // then
        assertThrows(AccessDeniedException.class, publishAction);
    }
}
