package com.educational.platform.courses.course.create.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.create.CreateCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CreateCourseCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;


    @SpyBean
    private CreateCourseCommandHandler sut;


    @Test
    void handle_validCourse_courseSaved() {
        // given
        final CreateCourseCommand command = new CreateCourseCommand("name", "description");

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findOne(Example.of(new Course(command)));
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrProperty("id").isNotNull();
        assertThat(course).hasFieldOrPropertyWithValue("name", "name");
        assertThat(course).hasFieldOrPropertyWithValue("description", "description");
    }
}
