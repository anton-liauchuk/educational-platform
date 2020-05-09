package com.educational.platform.course.reviews;

import com.educational.platform.common.domain.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

/**
 * Represents Course Rating model.
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CourseRating implements ValueObject {

    private double rating;

}
