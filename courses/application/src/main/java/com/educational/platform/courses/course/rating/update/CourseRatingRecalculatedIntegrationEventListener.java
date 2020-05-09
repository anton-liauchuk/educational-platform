package com.educational.platform.courses.course.rating.update;

import com.educational.platform.course.reviews.integration.event.CourseRatingRecalculatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event listener for {@link CourseRatingRecalculatedIntegrationEvent}.
 */
@Component
@RequiredArgsConstructor
public class CourseRatingRecalculatedIntegrationEventListener {

    private final UpdateCourseRatingCommandHandler handler;

    @Async
    @EventListener
    public void handleCourseRatingRecalculatedEvent(CourseRatingRecalculatedIntegrationEvent event) {
        handler.handle(new UpdateCourseRatingCommand(event.getCourseId(), event.getRating()));
    }

}
