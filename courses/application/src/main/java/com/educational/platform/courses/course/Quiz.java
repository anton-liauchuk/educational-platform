package com.educational.platform.courses.course;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import com.educational.platform.courses.course.create.CreateQuizCommand;

@Entity
@DiscriminatorValue("Quiz")
public class Quiz extends CurriculumItem {

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Question> questions;

	// for JPA
	protected Quiz() {
		super();
	}

	public Quiz(CreateQuizCommand command, Integer serialNumber, Course course) {
		super(command.getTitle(), command.getDescription(), course, serialNumber);
		this.questions = command.getQuestions().stream().map(questionCommand -> new Question(questionCommand.content(), this)).collect(Collectors.toList());
	}
}

