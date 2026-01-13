package com.educational.platform.courses.course.create;

import jakarta.validation.constraints.NotBlank;

import lombok.Builder;

/**
 * Create question command.
 */
@Builder
public record CreateQuestionCommand(@NotBlank String content) {

}
