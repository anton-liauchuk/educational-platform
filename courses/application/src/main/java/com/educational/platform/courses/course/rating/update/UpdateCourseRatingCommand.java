package com.educational.platform.courses.course.rating.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Update course rating command.
 */
// todo immutable
@Data
@RequiredArgsConstructor
public class UpdateCourseRatingCommand {

    private final UUID uuid;
    private final double rating;

}
