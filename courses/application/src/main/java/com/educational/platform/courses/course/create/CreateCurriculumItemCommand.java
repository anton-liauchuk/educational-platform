package com.educational.platform.courses.course.create;

public abstract class CreateCurriculumItemCommand {

	private final String title;
	private final String description;
	private final Integer serialNumber;

	public CreateCurriculumItemCommand(String title, String description, Integer serialNumber) {
		this.title = title;
		this.description = description;
		this.serialNumber = serialNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}
}
