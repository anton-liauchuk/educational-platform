package com.educational.platform.courses.course;

import com.educational.platform.common.domain.ValueObject;

import jakarta.persistence.Embeddable;

/**
 * Represents Course Rating model.
 */
@Embeddable
public record CourseRating(double rating) implements ValueObject {

}
