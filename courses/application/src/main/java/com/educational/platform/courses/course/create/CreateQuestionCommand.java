package com.educational.platform.courses.course.create;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Create question command.
 */
@Builder
@Data
@AllArgsConstructor
public class CreateQuestionCommand {

	@NotBlank
	private final String content;

}
