package com.educational.platform.course.reviews.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Represents Create Reviewable Course Command.
 */
@Data
@AllArgsConstructor
public class CreateReviewableCourseCommand {

    private final UUID uuid;

}
