package com.educational.platform.courses.course.create;

import com.educational.platform.courses.course.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCourseCommandHandlerTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CreateCourseCommandHandler sut;


    @Test
    void handle_validCourse_saveExecuted() {
        // given
        final CreateCourseCommand command = new CreateCourseCommand("name", "description");

        // when
        sut.handle(command);

        // then
        verify(repository).save(argThat(course -> "name".equals(course.getName()) && "description".equals(course.getDescription())));
    }
}
