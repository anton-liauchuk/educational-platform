package com.educational.platform.courses.course.rating.update;

import com.educational.platform.integration.events.CourseRatingRecalculatedIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseRatingRecalculatedIntegrationEventListenerTest {

    @Mock
    private UpdateCourseRatingCommandHandler handler;

    @InjectMocks
    private CourseRatingRecalculatedIntegrationEventListener sut;


    @Test
    void handleCourseRatingRecalculatedEvent_updateCourseRatingCommandExecuted() {
        // given
        final CourseRatingRecalculatedIntegrationEvent event = new CourseRatingRecalculatedIntegrationEvent(new Object(), 15, 3.7);

        // when
        sut.handleCourseRatingRecalculatedEvent(event);

        // then
        final ArgumentCaptor<UpdateCourseRatingCommand> argument = ArgumentCaptor.forClass(UpdateCourseRatingCommand.class);
        verify(handler).handle(argument.capture());
        final UpdateCourseRatingCommand updateCourseRatingCommand = argument.getValue();
        assertThat(updateCourseRatingCommand)
                .hasFieldOrPropertyWithValue("id", 15)
                .hasFieldOrPropertyWithValue("rating", 3.7);
    }

}
