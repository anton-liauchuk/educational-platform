package com.educational.platform.course.enrollments.student.create;

import com.educational.platform.course.enrollments.Student;
import com.educational.platform.course.enrollments.StudentRepository;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateStudentCommand} creates a student.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateStudentCommandHandler {

    private final StudentRepository studentRepository;

    /**
     * Creates student from command.
     *
     * @param command command
     */
    @CommandHandler
    public void handle(CreateStudentCommand command) {
        final Student student = new Student(command);
        studentRepository.save(student);
    }
}
