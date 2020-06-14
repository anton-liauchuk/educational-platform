package com.educational.platform.courses.teacher.create.integration;

import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import com.educational.platform.courses.teacher.create.CreateTeacherCommand;
import com.educational.platform.courses.teacher.create.CreateTeacherCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CreateTeacherCommandHandlerIntegrationTest {

    @Autowired
    private TeacherRepository repository;

    @SpyBean
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
