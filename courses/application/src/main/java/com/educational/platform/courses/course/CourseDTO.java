package com.educational.platform.courses.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	public static final String UUID_COLUMN = "course_uuid";
	public static final String NAME_COLUMN = "course_name";
	public static final String DESCRIPTION_COLUMN = "course_description";
	public static final String NUMBER_OF_STUDENTS_COLUMN = "course_numberOfStudents";

	UUID uuid;
	String name, description;
	int numberOfStudents;
	List<CurriculumItemDTO> curriculumItems;

	public CourseDTO(Object[] tuples, Map<String, Integer> aliasToIndexMap) {
		this.uuid = (UUID) tuples[aliasToIndexMap.get(UUID_COLUMN)];
		this.name = (String) tuples[aliasToIndexMap.get(NAME_COLUMN)];
		this.description = (String) tuples[aliasToIndexMap.get(DESCRIPTION_COLUMN)];
		this.numberOfStudents = ((NumberOfStudents) tuples[aliasToIndexMap.get(NUMBER_OF_STUDENTS_COLUMN)]).getNumber();
		this.curriculumItems = new ArrayList<>();
	}
}
