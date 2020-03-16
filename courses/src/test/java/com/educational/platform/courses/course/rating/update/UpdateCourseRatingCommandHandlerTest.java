package com.educational.platform.courses.course.rating.update;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.argThat;
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
        final Course correspondingCourse = new Course();
        correspondingCourse.setId(15);
        when(repository.findById(15)).thenReturn(Optional.of(correspondingCourse));

        // when
        sut.handle(command);

        // then
        verify(repository).save(argThat(course -> course.getId() == 15 && 3.2 == course.getRating().getRating()));
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
