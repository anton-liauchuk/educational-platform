package com.educational.platform.courses.teacher.create;

import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateTeacherCommand} creates a teacher.
 */
@Component
@Transactional
public class CreateTeacherCommandHandler {

    private final TeacherRepository teacherRepository;

    public CreateTeacherCommandHandler(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @CommandHandler
    public void handle(CreateTeacherCommand command) {
        teacherRepository.save(new Teacher(command));
    }

}
