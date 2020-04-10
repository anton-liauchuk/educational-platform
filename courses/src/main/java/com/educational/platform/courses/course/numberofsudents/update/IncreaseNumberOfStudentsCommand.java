package com.educational.platform.courses.course.numberofsudents.update;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Increase number of students command.
 */
@Data
@RequiredArgsConstructor
public class IncreaseNumberOfStudentsCommand {

    private final Integer id;

}
