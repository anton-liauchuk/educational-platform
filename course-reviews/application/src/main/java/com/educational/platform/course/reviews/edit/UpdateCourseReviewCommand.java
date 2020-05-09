package com.educational.platform.course.reviews.edit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

/**
 * Update Course Review Command.
 */
@Builder
@Data
@AllArgsConstructor
public class UpdateCourseReviewCommand {

    @NotNull
    private final UUID uuid;

    @Max(5)
    @PositiveOrZero
    @NotNull
    private final Double rating;
    private final String comment;

}
