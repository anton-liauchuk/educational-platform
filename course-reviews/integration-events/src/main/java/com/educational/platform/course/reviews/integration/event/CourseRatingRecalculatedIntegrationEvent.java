package com.educational.platform.course.reviews.integration.event;

import lombok.Value;

import java.util.UUID;

/**
 * Represents course rating recalculated integration event, should be published after course rating recalculation.
 */
@Value
public class CourseRatingRecalculatedIntegrationEvent {

	UUID courseId;
	double rating;

}
