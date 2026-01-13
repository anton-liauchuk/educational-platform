package com.educational.platform.courses.course;

import java.util.UUID;

/**
 * Represents course light dto.
 */
public record CourseLightDTO(UUID uuid, String name, String description, int numberOfStudents) {

	public CourseLightDTO(UUID uuid, String name, String description, NumberOfStudents numberOfStudents) {
        this(uuid, name, description, numberOfStudents.number());
    }

}
