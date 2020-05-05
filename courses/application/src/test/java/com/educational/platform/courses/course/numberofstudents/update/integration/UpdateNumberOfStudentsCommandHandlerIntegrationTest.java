package com.educational.platform.courses.course.numberofstudents.update.integration;

import com.educational.platform.courses.course.*;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.numberofsudents.update.IncreaseNumberOfStudentsCommand;
import com.educational.platform.courses.course.numberofsudents.update.IncreaseNumberOfStudentsCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpdateNumberOfStudentsCommandHandlerIntegrationTest {


    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @SpyBean
    private IncreaseNumberOfStudentsCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithUpdatedNumberOfStudents() {
        // given
        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course existingCourse = courseFactory.createFrom(createCourseCommand);
        repository.save(existingCourse);

        final UUID uuid = (UUID) ReflectionTestUtils.getField(existingCourse, "uuid");
        final IncreaseNumberOfStudentsCommand command = new IncreaseNumberOfStudentsCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("numberOfStudents", new NumberOfStudents(1));
    }
}
