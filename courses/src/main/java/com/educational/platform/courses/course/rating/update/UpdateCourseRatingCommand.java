package com.educational.platform.courses.course.rating.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Update course rating command.
 */
// todo immutable
@Data
@RequiredArgsConstructor
public class UpdateCourseRatingCommand {

    private final Integer id;
    private final double rating;

}
