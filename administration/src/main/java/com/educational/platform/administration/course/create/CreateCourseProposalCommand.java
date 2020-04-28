package com.educational.platform.administration.course.create;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Represents Create Course Proposal Command.
 */
@Data
@AllArgsConstructor
public class CreateCourseProposalCommand {

    private final UUID uuid;

}
