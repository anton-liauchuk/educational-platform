package com.educational.platform.courses.course.rating.update.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRating;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommand;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UpdateCourseRatingCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;


    @SpyBean
    private UpdateCourseRatingCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithUpdatedRating() {
        // given
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand("name", "description");
        final Course existingCourse = new Course(createCourseCommand);
        repository.save(existingCourse);

        final Integer id = (Integer) ReflectionTestUtils.getField(existingCourse, "id");
        final UpdateCourseRatingCommand command = new UpdateCourseRatingCommand(id, 4.3);

        // when
        sut.handle(command);

        // then
        assertThat(id).isNotNull();
        final Optional<Course> saved = repository.findById(id);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("rating", new CourseRating(4.3));
    }
}
