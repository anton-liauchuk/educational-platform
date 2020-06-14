package com.educational.platform.courses.course.create.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.create.CreateCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateCourseCommandHandlerIntegrationTest {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @SpyBean
    private CreateCourseCommandHandler sut;

    @Test
    @WithMockUser(roles = "TEACHER")
    void handle_validCourse_courseSaved() {
        // given
        final CreateCourseCommand command = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findAll()
                .stream()
                .filter(course -> "name".equals(ReflectionTestUtils.getField(course, "name")))
                .findAny();
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrProperty("id").isNotNull();
        assertThat(course).hasFieldOrPropertyWithValue("name", "name");
        assertThat(course).hasFieldOrPropertyWithValue("description", "description");
    }
}
