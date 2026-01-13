package com.educational.platform.administration.integration.event;

import java.util.UUID;

/**
 * Represents course approved by admin integration event, should be published after approval the course by admin.
 */
public record CourseApprovedByAdminIntegrationEvent(UUID courseId) {

}
