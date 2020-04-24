package com.educational.platform.course.reviews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Review Course Command.
 */
@Builder
@Data
@AllArgsConstructor
public class ReviewCourseCommand {

    @NotNull
    private final Integer courseId;

    @NotNull
    private final Integer studentId;

    @Max(5)
    @PositiveOrZero
    private final double rating;
    private final String comment;

}
