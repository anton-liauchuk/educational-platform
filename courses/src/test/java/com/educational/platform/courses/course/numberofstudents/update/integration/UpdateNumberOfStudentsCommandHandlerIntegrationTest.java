package com.educational.platform.courses.course.numberofstudents.update.integration;

import com.educational.platform.courses.course.*;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.numberofsudents.update.UpdateNumberOfStudentsCommand;
import com.educational.platform.courses.course.numberofsudents.update.UpdateNumberOfStudentsCommandHandler;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommand;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpdateNumberOfStudentsCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @SpyBean
    private UpdateNumberOfStudentsCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithUpdatedNumberOfStudents() {
        // given
        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course existingCourse = courseFactory.createFrom(createCourseCommand);
        repository.save(existingCourse);

        final Integer id = (Integer) ReflectionTestUtils.getField(existingCourse, "id");
        final UpdateNumberOfStudentsCommand command = new UpdateNumberOfStudentsCommand(id, 53);

        // when
        sut.handle(command);

        // then
        assertThat(id).isNotNull();
        final Optional<Course> saved = repository.findById(id);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("numberOfStudents", new NumberOfStudents(53));
    }
}
