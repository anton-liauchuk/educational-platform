package com.educational.platform.course.reviews;

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
public class CourseRating {

    private double rating;

}
