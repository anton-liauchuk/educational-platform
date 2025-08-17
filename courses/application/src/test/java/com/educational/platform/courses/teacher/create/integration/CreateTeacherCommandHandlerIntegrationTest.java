package com.educational.platform.courses.teacher.create.integration;

import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import com.educational.platform.courses.teacher.create.CreateTeacherCommand;
import com.educational.platform.courses.teacher.create.CreateTeacherCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateTeacherCommandHandlerIntegrationTest {

    @Autowired
    private TeacherRepository repository;

    @MockitoSpyBean
    private CreateTeacherCommandHandler sut;

    @Test
    void handle_validTeacher_saveExecuted() {
        // given
        final String username = "username";
        final CreateTeacherCommand command = new CreateTeacherCommand(username);

        // when
        sut.handle(command);

        // then
        final Teacher saved = repository.findByUsername(username);
        assertThat(saved)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrProperty("id").isNotNull();
    }
}
