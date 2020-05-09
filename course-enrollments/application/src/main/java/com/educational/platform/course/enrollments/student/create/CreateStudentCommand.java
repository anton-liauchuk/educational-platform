package com.educational.platform.course.enrollments.student.create;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents Create student command.
 */
@Data
@AllArgsConstructor
public class CreateStudentCommand {

    private final String username;

}
