package com.educational.platform.course.reviews;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Represents course review dto.
 */
@Builder
@AllArgsConstructor
@Value
public class CourseReviewDTO {

	UUID uuid, course;
	String username, comment;
	double rating;

}
