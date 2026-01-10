package com.educational.platform.courses.course;

import org.hibernate.query.TupleTransformer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents course dto result transformer.
 */
public class CourseDTOResultTransformer implements TupleTransformer<CourseDTO> {

    private final Map<UUID, CourseDTO> courseDTOMap = new LinkedHashMap<>();

    @Override
    public CourseDTO transformTuple(
            Object[] tuple, String[] aliases
    ) {

        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        UUID uuid = UUID.fromString(String.valueOf(tuple[aliasToIndexMap.get(CourseDTO.UUID_COLUMN)]));

        CourseDTO courseDTO = courseDTOMap.computeIfAbsent(
                uuid,
                id -> new CourseDTO(tuple, aliasToIndexMap)
        );
        if (aliasToIndexMap.get(CurriculumItemDTO.TYPE).toString().equals("Lecture")) {
            courseDTO.curriculumItems().add(new LectureDTO(uuid, tuple, aliasToIndexMap));
        } else if (aliasToIndexMap.get(CurriculumItemDTO.TYPE).toString().equals("Quiz")) {
            courseDTO.curriculumItems().add(new QuizDTO(uuid, tuple, aliasToIndexMap));
        }
        return courseDTO;
    }

    public Map<String, Integer> aliasToIndexMap(
            String[] aliases
    ) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }

        return aliasToIndexMap;
    }
}
