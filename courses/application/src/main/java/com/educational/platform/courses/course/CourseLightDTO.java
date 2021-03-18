package com.educational.platform.courses.course;

import java.util.UUID;

import lombok.Value;

/**
 * Represents course light dto.
 */
@Value
public class CourseLightDTO {

	UUID uuid;
	String name, description;
	int numberOfStudents;

	public CourseLightDTO(UUID uuid, String name, String description, NumberOfStudents numberOfStudents) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.numberOfStudents = numberOfStudents.getNumber();
	}

}
