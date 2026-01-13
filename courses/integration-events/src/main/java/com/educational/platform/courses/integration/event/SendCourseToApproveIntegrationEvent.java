package com.educational.platform.courses.integration.event;

import java.util.UUID;

/**
 * Represents send course to approve integration event.
 */
public record SendCourseToApproveIntegrationEvent(UUID courseId) {

}
