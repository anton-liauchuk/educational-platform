package com.educational.platform.courses.course;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.educational.platform.courses.course.create.CreateLectureCommand;

@Entity
@DiscriminatorValue("Lecture")
public class Lecture extends CurriculumItem {

	private String content;

	// for JPA
	private Lecture() {
		super();
	}

	public Lecture(CreateLectureCommand command, Integer serialNumber, Course course) {
		super(command.getTitle(), command.getDescription(), course, serialNumber);
		this.content = command.getText();
	}
}

