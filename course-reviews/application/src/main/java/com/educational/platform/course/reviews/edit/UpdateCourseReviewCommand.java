package com.educational.platform.course.reviews.edit;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

/**
 * Update Course Review Command.
 */
public record UpdateCourseReviewCommand(@NotNull UUID uuid, @Max(5) @PositiveOrZero @NotNull Double rating,
                                        String comment) {

}
