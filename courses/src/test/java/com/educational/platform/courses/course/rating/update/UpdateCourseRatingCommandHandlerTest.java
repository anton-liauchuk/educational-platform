package com.educational.platform.courses.course.rating.update;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRating;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCourseRatingCommandHandlerTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private UpdateCourseRatingCommandHandler sut;


    @Test
    void handle_existingCourse_courseSavedWithUpdatedRating() {
        // given
        final UpdateCourseRatingCommand command = new UpdateCourseRatingCommand(15, 3.2);

        final CreateCourseCommand createCourseCommand = new CreateCourseCommand("name", "description");
        final Course correspondingCourse = new Course(createCourseCommand);
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
                .hasFieldOrPropertyWithValue("rating", new CourseRating(3.2));
    }


    @Test
    void handle_invalidId_resourceNotFoundException() {
        // given
        final UpdateCourseRatingCommand command = new UpdateCourseRatingCommand(15, 3.2);
        when(repository.findById(15)).thenReturn(Optional.empty());

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(command);

        // then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(handle);
    }
}
