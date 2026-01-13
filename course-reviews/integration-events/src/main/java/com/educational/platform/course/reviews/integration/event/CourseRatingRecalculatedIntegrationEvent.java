package com.educational.platform.course.reviews.integration.event;

import java.util.UUID;

/**
 * Represents course rating recalculated integration event, should be published after course rating recalculation.
 */
public record CourseRatingRecalculatedIntegrationEvent(UUID courseId, double rating) {

}
