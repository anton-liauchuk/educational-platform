package com.educational.platform.course.reviews;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Represents Comment model.
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Comment {

    private String comment;

}
