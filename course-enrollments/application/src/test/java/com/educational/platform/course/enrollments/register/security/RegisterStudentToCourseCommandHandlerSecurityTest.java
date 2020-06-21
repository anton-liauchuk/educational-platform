package com.educational.platform.course.enrollments.register.security;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;
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

@Sql(scripts = "classpath:course.sql")
@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class RegisterStudentToCourseCommandHandlerSecurityTest {

    private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
    private final String studentUsername = "student";
    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;
    @SpyBean
    private RegisterStudentToCourseCommandHandler sut;

    @Test
    @WithMockUser(username = "student", roles = "STUDENT")
    void handle_userIsStudent_studentEnrolled() {
        // given
        var command = RegisterStudentToCourseCommand.builder()
                .courseId(courseUuid)
                .student(studentUsername)
                .build();

        // when
        var result = sut.handle(command);

        // then
        final Optional<CourseEnrollment> saved = courseEnrollmentRepository.findByUuid(result);
        assertThat(saved).isNotEmpty();
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void handle_userIsTeacher_accessDeniedException() {
        // given
        var command = RegisterStudentToCourseCommand.builder()
                .courseId(courseUuid)
                .student(studentUsername)
                .build();

        // when
        final Executable publishAction = () -> sut.handle(command);

        // then
        assertThrows(AccessDeniedException.class, publishAction);
    }
}
