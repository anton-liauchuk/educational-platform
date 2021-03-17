package com.educational.platform.courses.course;

import java.util.Map;
import java.util.UUID;

public class LectureDTO extends CurriculumItemDTO {

	public static final String TEXT = "text";

	String text;

	public LectureDTO(UUID uuid, Object[] tuples, Map<String, Integer> aliasToIndexMap) {
		super(uuid, (String) tuples[aliasToIndexMap.get(CurriculumItemDTO.TITLE)], (String) tuples[aliasToIndexMap.get(CurriculumItemDTO.DESCRIPTION)], (Integer) tuples[aliasToIndexMap.get(CurriculumItemDTO.SERIAL_NUMBER)]);
		this.text = (String) tuples[aliasToIndexMap.get(LectureDTO.TEXT)];
	}
}
