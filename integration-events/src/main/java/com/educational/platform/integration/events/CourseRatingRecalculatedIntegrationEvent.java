package com.educational.platform.integration.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Represents course rating recalculated integration event, should be published after course rating recalculation.
 */
@Getter
public class CourseRatingRecalculatedIntegrationEvent extends ApplicationEvent {

    private final int courseId;
    private final double rating;


    /**
     * Create a new {@code CourseRatingRecalculatedIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     * @param rating   updated rating value
     */
    public CourseRatingRecalculatedIntegrationEvent(Object source, int courseId, double rating) {
        super(source);
        this.courseId = courseId;
        this.rating = rating;
    }

}
