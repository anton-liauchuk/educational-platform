package com.educational.platform.courses.course;

import com.educational.platform.common.domain.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

/**
 * Represents number of students model.
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class NumberOfStudents implements ValueObject {

    private int number;

}
