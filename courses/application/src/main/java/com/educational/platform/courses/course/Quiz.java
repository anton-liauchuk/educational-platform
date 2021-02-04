package com.educational.platform.courses.course;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.educational.platform.courses.course.create.CreateQuizCommand;

@Entity
public class Quiz extends CurriculumItem {

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Question> questions;

	// for JPA
	protected Quiz() {
		super();
	}

	public Quiz(CreateQuizCommand command, Integer serialNumber, Course course) {
		super(command.getTitle(), command.getDescription(), course, serialNumber);
		this.questions = command.getQuestions().stream().map(questionCommand -> new Question(questionCommand.getContent(), this)).collect(Collectors.toList());
	}
}

