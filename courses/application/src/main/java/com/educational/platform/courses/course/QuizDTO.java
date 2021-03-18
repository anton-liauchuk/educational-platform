package com.educational.platform.courses.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class QuizDTO extends CurriculumItemDTO {

	List<QuestionDTO> questions;

	public QuizDTO(UUID uuid, Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(uuid, (String) tuple[aliasToIndexMap.get(CurriculumItemDTO.TITLE)], (String) tuple[aliasToIndexMap.get(CurriculumItemDTO.DESCRIPTION)], (Integer) tuple[aliasToIndexMap.get(CurriculumItemDTO.SERIAL_NUMBER)]);
		this.questions = new ArrayList<>();
	}
}
