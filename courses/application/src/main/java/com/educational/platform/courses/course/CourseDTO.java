package com.educational.platform.courses.course;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Represents course dto.
 */
@Builder
@AllArgsConstructor
@Value
public class CourseDTO {

	UUID uuid;
	String name, description;
	int numberOfStudents;

	public CourseDTO(UUID uuid, String name, String description, NumberOfStudents numberOfStudents) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.numberOfStudents = numberOfStudents.getNumber();
	}
}
