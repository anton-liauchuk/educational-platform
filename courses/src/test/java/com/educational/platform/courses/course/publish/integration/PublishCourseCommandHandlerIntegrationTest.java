package com.educational.platform.courses.course.publish.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.Status;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PublishCourseCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @SpyBean
    private PublishCourseCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithStatusPublished() {
        // given
        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course existingCourse = courseFactory.createFrom(createCourseCommand);
        repository.save(existingCourse);

        final Integer id = (Integer) ReflectionTestUtils.getField(existingCourse, "id");
        final PublishCourseCommand command = new PublishCourseCommand(id);

        // when
        sut.handle(command);

        // then
        assertThat(id).isNotNull();
        final Optional<Course> saved = repository.findById(id);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("status", Status.PUBLISHED);
    }
}
