package com.educational.platform.courses.course.create;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Create course command.
 */
@Builder
@Data
@AllArgsConstructor
public class CreateCourseCommand {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    private final List<CreateCurriculumItemCommand> curriculumItems;

}
