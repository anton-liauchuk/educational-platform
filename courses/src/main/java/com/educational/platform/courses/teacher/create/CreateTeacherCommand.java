package com.educational.platform.courses.teacher.create;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Create teacher command.
 */
@Data
@AllArgsConstructor
public class CreateTeacherCommand {

    private String username;

}
