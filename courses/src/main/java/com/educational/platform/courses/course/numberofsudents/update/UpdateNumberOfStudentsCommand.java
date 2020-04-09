package com.educational.platform.courses.course.numberofsudents.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Update course rating command.
 */
@Data
@RequiredArgsConstructor
public class UpdateNumberOfStudentsCommand {

    private final Integer id;
    private final int number;

}
