package com.educational.platform.courses.course.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create course command.
 */
@Builder
@Data
@AllArgsConstructor
public class CreateCourseCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
