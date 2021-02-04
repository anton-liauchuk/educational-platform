package com.educational.platform.courses.course.create;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizCommand extends CreateCurriculumItemCommand {

	private String text;
	private List<CreateQuestionCommand> questions;

	@Builder
	public CreateQuizCommand(List<CreateQuestionCommand> questions, String title, String description, Integer serialNumber, String text) {
		super(title, description, serialNumber);
		this.text = text;
		this.questions = questions;
	}

}
