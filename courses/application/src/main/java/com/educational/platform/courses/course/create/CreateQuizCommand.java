package com.educational.platform.courses.course.create;

import java.util.List;

public class CreateQuizCommand extends CreateCurriculumItemCommand {

	private final String text;
	private final List<CreateQuestionCommand> questions;

	public CreateQuizCommand(List<CreateQuestionCommand> questions, String title, String description, Integer serialNumber, String text) {
		super(title, description, serialNumber);
		this.text = text;
		this.questions = questions;
	}

	public String getText() {
		return text;
	}

	public List<CreateQuestionCommand> getQuestions() {
		return questions;
	}

	public static CreateQuizCommandBuilder builder() {
		return new CreateQuizCommandBuilder();
	}

	public static final class CreateQuizCommandBuilder {
		private String text;
		private List<CreateQuestionCommand> questions;
		private String title;
		private String description;
		private Integer serialNumber;

		private CreateQuizCommandBuilder() {
		}

		public CreateQuizCommandBuilder text(String text) {
			this.text = text;
			return this;
		}

		public CreateQuizCommandBuilder questions(List<CreateQuestionCommand> questions) {
			this.questions = questions;
			return this;
		}

		public CreateQuizCommandBuilder title(String title) {
			this.title = title;
			return this;
		}

		public CreateQuizCommandBuilder description(String description) {
			this.description = description;
			return this;
		}

		public CreateQuizCommandBuilder serialNumber(Integer serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}

		public CreateQuizCommand build() {
			return new CreateQuizCommand(questions, title, description, serialNumber, text);
		}
	}
}
