package com.educational.platform.courses.course.rating.update.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRating;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.Status;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommand;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    void handle_existingCourse_courseSavedWithStatusPublished() {
        // given
        final Course existingCourse = new Course();
        existingCourse.setName("name");
        existingCourse.setName("description");
        existingCourse.setStatus(Status.DRAFT);
        existingCourse.setRating(new CourseRating(3.2));
        repository.save(existingCourse);

        final UpdateCourseRatingCommand command = new UpdateCourseRatingCommand(existingCourse.getId(), 4.3);

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findById(existingCourse.getId());
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course.getRating()).isEqualTo(new CourseRating(4.3));
    }
}
