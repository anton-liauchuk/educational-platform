package com.educational.platform.courses.course.approve;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Send course to approve command.
 */
@Data
@AllArgsConstructor
public class SendCourseToApproveCommand {

    private Integer id;

}
