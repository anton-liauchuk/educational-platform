package com.educational.platform.courses.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CreateCurriculumItemCommand {

	private String title;
	private String description;
	private Integer serialNumber;

}
