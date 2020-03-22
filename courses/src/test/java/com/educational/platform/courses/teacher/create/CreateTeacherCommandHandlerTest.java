package com.educational.platform.courses.teacher.create;

import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateTeacherCommandHandlerTest {

    @Mock
    private TeacherRepository repository;

    @InjectMocks
    private CreateTeacherCommandHandler sut;


    @Test
    void handle_validTeacher_saveExecuted() {
        // given
        final CreateTeacherCommand command = new CreateTeacherCommand("username");

        // when
        sut.handle(command);

        // then
        final ArgumentCaptor<Teacher> argument = ArgumentCaptor.forClass(Teacher.class);
        verify(repository).save(argument.capture());
        final Teacher saved = argument.getValue();
        assertThat(saved)
                .hasFieldOrPropertyWithValue("username", "username")
                .hasFieldOrProperty("id").isNotNull();
    }
}
