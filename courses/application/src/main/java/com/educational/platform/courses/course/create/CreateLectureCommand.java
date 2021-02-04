package com.educational.platform.courses.course.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLectureCommand extends CreateCurriculumItemCommand {

	private String text;

	@Builder
	public CreateLectureCommand(String title, String description, Integer serialNumber, String text) {
		super(title, description, serialNumber);
		this.text = text;
	}

}
