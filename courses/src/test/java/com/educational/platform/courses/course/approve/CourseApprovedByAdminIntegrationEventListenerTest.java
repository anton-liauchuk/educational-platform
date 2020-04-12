package com.educational.platform.courses.course.approve;

import com.educational.platform.integration.events.CourseApprovedByAdminIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseApprovedByAdminIntegrationEventListenerTest {

    @Mock
    private ApproveCourseCommandHandler handler;

    @InjectMocks
    private CourseApprovedByAdminIntegrationEventListener sut;


    @Test
    void handleCourseApprovedByAdminEvent_approveCourseCommandExecuted() {
        // given
        final CourseApprovedByAdminIntegrationEvent event = new CourseApprovedByAdminIntegrationEvent(new Object(), 15);

        // when
        sut.handleCourseApprovedByAdminEvent(event);

        // then
        final ArgumentCaptor<ApproveCourseCommand> argument = ArgumentCaptor.forClass(ApproveCourseCommand.class);
        verify(handler).handle(argument.capture());
        final ApproveCourseCommand approveCourseCommand = argument.getValue();
        assertThat(approveCourseCommand)
                .hasFieldOrPropertyWithValue("id", 15);
    }

}
