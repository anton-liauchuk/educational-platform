package com.educational.platform.course.reviews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Represents update course review request.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseReviewRequest {

    @Max(5)
    @PositiveOrZero
    @NotNull
    private Double rating;
    private String comment;

}
