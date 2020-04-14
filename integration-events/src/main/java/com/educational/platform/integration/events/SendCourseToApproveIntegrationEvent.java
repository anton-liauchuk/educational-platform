package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Represents send course to approve integration event.
 */
@Getter
public class SendCourseToApproveIntegrationEvent extends ApplicationEvent {

    private final int courseId;


    /**
     * Create a new {@code SendCourseToApproveIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     */
    public SendCourseToApproveIntegrationEvent(Object source, int courseId) {
        super(source);
        this.courseId = courseId;
    }

}
