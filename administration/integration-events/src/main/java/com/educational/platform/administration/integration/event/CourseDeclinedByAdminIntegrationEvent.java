package com.educational.platform.administration.integration.event;

import java.util.UUID;

/**
 * Represents course declined by admin integration event.
 */
public record CourseDeclinedByAdminIntegrationEvent(UUID courseId) {
}
