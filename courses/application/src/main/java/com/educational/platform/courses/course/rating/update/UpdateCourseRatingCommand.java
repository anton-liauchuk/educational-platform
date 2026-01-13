package com.educational.platform.courses.course.rating.update;

import java.util.UUID;

/**
 * Update course rating command.
 */
public record UpdateCourseRatingCommand(UUID uuid, double rating) {

}
