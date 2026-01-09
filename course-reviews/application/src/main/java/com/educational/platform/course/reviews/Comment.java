package com.educational.platform.course.reviews;

import com.educational.platform.common.domain.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents Comment model.
 */
@Embeddable
public record Comment(String comment) implements ValueObject {

}
