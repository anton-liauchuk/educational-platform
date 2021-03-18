package com.educational.platform.courses.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String content;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	// for JPA
	protected Question() {
		super();
	}

	public Question(String content, Quiz quiz) {
		this.content = content;
		this.quiz = quiz;
	}

}
