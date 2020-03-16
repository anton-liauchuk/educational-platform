package com.educational.platform.courses.course.publish.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.Status;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.create.CreateCourseCommandHandler;
import com.educational.platform.courses.course.publish.PublishCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PublishCourseCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;


    @SpyBean
    private PublishCourseCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithStatusPublished() {
        // given
        final Course existingCourse = new Course();
        existingCourse.setName("name");
        existingCourse.setName("description");
        existingCourse.setStatus(Status.DRAFT);
        repository.save(existingCourse);

        final PublishCourseCommand command = new PublishCourseCommand(existingCourse.getId());

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findById(existingCourse.getId());
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course.getStatus()).isEqualTo(Status.PUBLISHED);
    }
}
