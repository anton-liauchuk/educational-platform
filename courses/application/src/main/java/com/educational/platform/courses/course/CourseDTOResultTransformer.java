package com.educational.platform.courses.course;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.transform.ResultTransformer;

/**
 * Represents health indicator config dto result transformer.
 */
public class CourseDTOResultTransformer implements ResultTransformer {

	private final Map<UUID, CourseDTO> courseDTOMap = new LinkedHashMap<>();

	@Override
	public Object transformTuple(
			Object[] tuple, String[] aliases
	) {

		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

		UUID uuid = UUID.fromString(String.valueOf(tuple[aliasToIndexMap.get(CourseDTO.UUID_COLUMN)]));

		CourseDTO courseDTO = courseDTOMap.computeIfAbsent(
				uuid,
				id -> new CourseDTO(tuple, aliasToIndexMap)
		);
		if (aliasToIndexMap.get(CurriculumItemDTO.TYPE).toString().equals("Lecture")) {
			courseDTO.getCurriculumItems().add(new LectureDTO(uuid, tuple, aliasToIndexMap));
		} else if (aliasToIndexMap.get(CurriculumItemDTO.TYPE).toString().equals("Quiz")) {
			courseDTO.getCurriculumItems().add(new QuizDTO(uuid, tuple, aliasToIndexMap));
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

	@Override
	public List<CourseDTO> transformList(List collection) {
		return new ArrayList<>(courseDTOMap.values());
	}
}
