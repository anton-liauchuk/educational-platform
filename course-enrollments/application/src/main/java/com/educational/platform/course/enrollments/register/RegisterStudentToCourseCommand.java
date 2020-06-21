package com.educational.platform.course.enrollments.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Represents register student to course command.
 */
@Builder
@Data
@AllArgsConstructor
public class RegisterStudentToCourseCommand {

    @NotNull
    private final UUID courseId;

}
