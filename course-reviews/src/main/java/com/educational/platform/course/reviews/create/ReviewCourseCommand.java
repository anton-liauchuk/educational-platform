package com.educational.platform.course.reviews.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

/**
 * Review Course Command.
 */
@Builder
@Data
@AllArgsConstructor
public class ReviewCourseCommand {

    @NotNull
    private final UUID courseId;

    @NotNull
    private final String reviewer;

    @Max(5)
    @PositiveOrZero
    private final double rating;
    private final String comment;

}
