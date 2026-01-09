package com.educational.platform.course.reviews;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Represents review course request.
 */
public record ReviewCourseRequest(@Max(5)
                                  @PositiveOrZero
                                  @NotNull
                                  Double rating, String comment) {

}
