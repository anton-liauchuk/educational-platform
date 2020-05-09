package com.educational.platform.course.reviews.integration.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 * Represents course rating recalculated integration event, should be published after course rating recalculation.
 */
@Getter
public class CourseRatingRecalculatedIntegrationEvent extends ApplicationEvent {

    private final UUID courseId;
    private final double rating;


    /**
     * Create a new {@code CourseRatingRecalculatedIntegrationEvent}.
     *
     * @param source   the object on which the event initially occurred or with
     *                 which the event is associated (never {@code null})
     * @param courseId course id
     * @param rating   updated rating value
     */
    public CourseRatingRecalculatedIntegrationEvent(Object source, UUID courseId, double rating) {
        super(source);
        this.courseId = courseId;
        this.rating = rating;
    }

}
