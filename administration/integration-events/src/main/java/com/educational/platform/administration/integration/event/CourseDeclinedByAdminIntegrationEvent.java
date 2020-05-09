package com.educational.platform.administration.integration.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 * Represents course declined by admin integration event.
 */
@Getter
public class CourseDeclinedByAdminIntegrationEvent extends ApplicationEvent {


    private final UUID courseId;


    /**
     * Create a new {@code CourseDeclinedByAdminIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     */
    public CourseDeclinedByAdminIntegrationEvent(Object source, UUID courseId) {
        super(source);
        this.courseId = courseId;
    }
}
