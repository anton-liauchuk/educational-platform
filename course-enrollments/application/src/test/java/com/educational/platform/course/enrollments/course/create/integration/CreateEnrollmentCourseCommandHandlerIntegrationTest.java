package com.educational.platform.course.enrollments.course.create.integration;

import com.educational.platform.course.enrollments.EnrollCourse;
import com.educational.platform.course.enrollments.EnrollCourseRepository;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.course.create.CreateEnrollmentCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
public class CreateEnrollmentCourseCommandHandlerIntegrationTest {

    @Autowired
    private EnrollCourseRepository repository;

    @SpyBean
    private CreateEnrollmentCourseCommandHandler sut;

    @Test
    void handle_validCommand_courseSaved() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CreateCourseCommand command = new CreateCourseCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<EnrollCourse> saved = repository.findOne(Example.of(new EnrollCourse(command)));
        assertThat(saved).isNotEmpty();
        final EnrollCourse course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("uuid", uuid);
    }
}
