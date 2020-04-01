package com.educational.platform.courses.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create course command.
 */
@Data
@AllArgsConstructor
public class CreateCourseCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
