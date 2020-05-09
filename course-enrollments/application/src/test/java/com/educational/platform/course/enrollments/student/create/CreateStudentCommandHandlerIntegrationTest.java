package com.educational.platform.course.enrollments.student.create;

import com.educational.platform.course.enrollments.Student;
import com.educational.platform.course.enrollments.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
public class CreateStudentCommandHandlerIntegrationTest {

    @Autowired
    private StudentRepository repository;

    @SpyBean
    private CreateStudentCommandHandler sut;

    @Test
    void handle_validCommand_studentSaved() {
        // given
        final CreateStudentCommand command = new CreateStudentCommand("username");

        // when
        sut.handle(command);

        // then
        final Optional<Student> saved = repository.findOne(Example.of(new Student(command)));
        assertThat(saved).isNotEmpty();
        final Student student = saved.get();
        assertThat(student).hasFieldOrPropertyWithValue("username", "username");
    }
}
