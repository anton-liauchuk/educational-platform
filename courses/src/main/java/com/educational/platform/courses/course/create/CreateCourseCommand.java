package com.educational.platform.courses.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Create course command.
 */
@Data
@AllArgsConstructor
public class CreateCourseCommand {

    private String name;
    private String description;

}
