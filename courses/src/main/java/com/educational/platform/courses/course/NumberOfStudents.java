package com.educational.platform.courses.course;

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
public class NumberOfStudents {

    private int number;

}
