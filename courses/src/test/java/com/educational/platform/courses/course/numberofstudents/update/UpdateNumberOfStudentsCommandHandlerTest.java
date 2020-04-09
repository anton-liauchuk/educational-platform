package com.educational.platform.courses.course.numberofstudents.update;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.*;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.numberofsudents.update.UpdateNumberOfStudentsCommand;
import com.educational.platform.courses.course.numberofsudents.update.UpdateNumberOfStudentsCommandHandler;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommand;
import com.educational.platform.courses.course.rating.update.UpdateCourseRatingCommandHandler;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateNumberOfStudentsCommandHandlerTest {

    private CourseFactory courseFactory;

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private UpdateNumberOfStudentsCommandHandler sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        courseFactory = new CourseFactory(validator);
    }

    @Test
    void handle_existingCourse_courseSavedWithUpdatedNumberOfStudents() {
        // given
        final UpdateNumberOfStudentsCommand command = new UpdateNumberOfStudentsCommand(15, 53);

        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course correspondingCourse = courseFactory.createFrom(createCourseCommand);
        when(repository.findById(15)).thenReturn(Optional.of(correspondingCourse));

        // when
        sut.handle(command);

        // then
        final ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
        verify(repository).save(argument.capture());
        final Course course = argument.getValue();
        assertThat(course)
                .hasFieldOrPropertyWithValue("name", "name")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("numberOfStudents", new NumberOfStudents(53));
    }


    @Test
    void handle_invalidId_resourceNotFoundException() {
        // given
        final UpdateNumberOfStudentsCommand command = new UpdateNumberOfStudentsCommand(15, 53);
        when(repository.findById(15)).thenReturn(Optional.empty());

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(command);

        // then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(handle);
    }
}
