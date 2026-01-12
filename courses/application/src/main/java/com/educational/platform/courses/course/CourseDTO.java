package com.educational.platform.courses.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents course dto.
 */
public record CourseDTO(UUID uuid, String name, String description, int numberOfStudents,
                        List<CurriculumItemDTO> curriculumItems) {

    public static final String UUID_COLUMN = "course_uuid";
    public static final String NAME_COLUMN = "course_name";
    public static final String DESCRIPTION_COLUMN = "course_description";
    public static final String NUMBER_OF_STUDENTS_COLUMN = "course_numberOfStudents";


    public CourseDTO(Object[] tuples, Map<String, Integer> aliasToIndexMap) {
        this((UUID) tuples[aliasToIndexMap.get(UUID_COLUMN)],
                (String) tuples[aliasToIndexMap.get(NAME_COLUMN)],
                (String) tuples[aliasToIndexMap.get(DESCRIPTION_COLUMN)],
                ((NumberOfStudents) tuples[aliasToIndexMap.get(NUMBER_OF_STUDENTS_COLUMN)]).number(),
                new ArrayList<>());
    }
}
