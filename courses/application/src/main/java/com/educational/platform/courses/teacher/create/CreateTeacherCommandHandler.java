package com.educational.platform.courses.teacher.create;

import com.educational.platform.common.domain.CommandHandler;
import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link CreateTeacherCommand} creates a teacher.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class CreateTeacherCommandHandler implements CommandHandler {

    private final TeacherRepository teacherRepository;

    public void handle(CreateTeacherCommand command) {
        teacherRepository.save(new Teacher(command));
    }

}
