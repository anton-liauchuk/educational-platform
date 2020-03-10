package com.educational.platform.courses.teacher.create;

import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Command handler for {@link CreateTeacherCommand} creates a teacher.
 */
@RequiredArgsConstructor
@Component
public class CreateTeacherCommandHandler {


    private final TeacherRepository teacherRepository;


    public void handle(CreateTeacherCommand command) {
        teacherRepository.save(new Teacher(command));
    }

}
