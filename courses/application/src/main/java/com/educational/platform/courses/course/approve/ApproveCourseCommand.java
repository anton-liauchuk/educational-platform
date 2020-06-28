package com.educational.platform.courses.course.approve;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Approve course command.
 */
@Data
@AllArgsConstructor
public class ApproveCourseCommand {

    private final UUID uuid;

}
