package com.educational.platform.courses.course;

import com.educational.platform.common.domain.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents number of students model.
 */
@Embeddable
public record NumberOfStudents(int number) implements ValueObject {

}
