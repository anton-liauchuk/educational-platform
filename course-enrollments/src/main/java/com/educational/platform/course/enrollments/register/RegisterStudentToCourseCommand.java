package com.educational.platform.course.enrollments.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Represents register student to course command.
 */
@Builder
@Data
@AllArgsConstructor
public class RegisterStudentToCourseCommand {

    @NotNull
    private final Integer courseId;

    @NotNull
    private final Integer studentId;

}
