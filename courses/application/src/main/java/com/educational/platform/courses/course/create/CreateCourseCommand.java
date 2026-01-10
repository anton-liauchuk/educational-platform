package com.educational.platform.courses.course.create;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.NotBlank;

/**
 * Create course command.
 */
@Builder
public record CreateCourseCommand(@NotBlank String name, @NotBlank String description,
                                  List<CreateCurriculumItemCommand> curriculumItems) {

}
