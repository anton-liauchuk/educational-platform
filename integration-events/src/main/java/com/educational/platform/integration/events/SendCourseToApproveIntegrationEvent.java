package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 * Represents send course to approve integration event.
 */
@Getter
public class SendCourseToApproveIntegrationEvent extends ApplicationEvent {

    private final UUID courseId;


    /**
     * Create a new {@code SendCourseToApproveIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     */
    public SendCourseToApproveIntegrationEvent(Object source, UUID courseId) {
        super(source);
        this.courseId = courseId;
    }

}
