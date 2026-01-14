package com.educational.platform.courses.course.create;

import jakarta.validation.constraints.NotBlank;

/**
 * Create question command.
 */
public record CreateQuestionCommand(@NotBlank String content) {

}
