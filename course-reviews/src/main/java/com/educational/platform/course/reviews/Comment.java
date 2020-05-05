package com.educational.platform.course.reviews;

import com.educational.platform.common.domain.ValueObject;
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
public class Comment implements ValueObject {

    private String comment;

}
