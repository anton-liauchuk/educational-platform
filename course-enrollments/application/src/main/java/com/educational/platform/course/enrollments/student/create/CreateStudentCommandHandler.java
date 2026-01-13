package com.educational.platform.course.enrollments.student.create;

import com.educational.platform.course.enrollments.student.Student;
import com.educational.platform.course.enrollments.student.StudentRepository;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateStudentCommand} creates a student.
 */
@Component
@Transactional
public class CreateStudentCommandHandler {

    private final StudentRepository studentRepository;

    public CreateStudentCommandHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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
