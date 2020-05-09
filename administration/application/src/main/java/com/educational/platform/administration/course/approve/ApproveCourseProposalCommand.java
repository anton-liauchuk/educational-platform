package com.educational.platform.administration.course.approve;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Approve course proposal command.
 */
@Data
@AllArgsConstructor
public class ApproveCourseProposalCommand {

    private final UUID uuid;

}
