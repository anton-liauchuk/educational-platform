package com.educational.platform.courses;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents Course Create Request.
 */
public record CreateCourseRequest(@NotBlank String name, @NotBlank String description) {

}
