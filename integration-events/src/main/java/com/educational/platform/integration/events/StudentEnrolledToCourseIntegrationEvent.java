package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Represents student enrolled to course integration event, should be published after enrollment to course by student.
 */
@Getter
public class StudentEnrolledToCourseIntegrationEvent extends ApplicationEvent {


    private final int courseId;
    private final String username;


    /**
     * Create a new {@code StudentEnrolledToCourseIntegrationEvent}.
     *  @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     * @param username student username
     */
    public StudentEnrolledToCourseIntegrationEvent(Object source, int courseId, String username) {
        super(source);
        this.courseId = courseId;
        this.username = username;
    }
}
