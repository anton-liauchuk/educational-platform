package com.educational.platform.course.enrollments.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Represents Create Course Command.
 */
@Data
@AllArgsConstructor
public class CreateCourseCommand {

    private final UUID uuid;

}
