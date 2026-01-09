package com.educational.platform.course.reviews.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

/**
 * Review Course Command.
 */
public record ReviewCourseCommand(@NotNull UUID courseId, @Max(5) @PositiveOrZero @NotNull Double rating,
                                  String comment) {

}
