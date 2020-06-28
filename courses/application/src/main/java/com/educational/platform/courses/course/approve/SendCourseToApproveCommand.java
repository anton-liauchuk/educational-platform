package com.educational.platform.courses.course.approve;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Send course to approve command.
 */
@Data
@AllArgsConstructor
public class SendCourseToApproveCommand {

    private final UUID uuid;

}
