package com.educational.platform.course.reviews.query;

import java.util.UUID;

import lombok.Value;

/**
 * Represents list course reviews by course uuid query.
 */
@Value
public class ListCourseReviewsByCourseUUIDQuery {

	UUID uuid;
}
