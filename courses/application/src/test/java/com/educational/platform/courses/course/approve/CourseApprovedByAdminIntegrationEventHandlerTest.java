package com.educational.platform.courses.course.approve;

import com.educational.platform.administration.integration.event.CourseApprovedByAdminIntegrationEvent;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseApprovedByAdminIntegrationEventHandlerTest {

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private CourseApprovedByAdminIntegrationEventHandler sut;

    @Test
    void handleCourseApprovedByAdminEvent_approveCourseCommandExecuted() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final CourseApprovedByAdminIntegrationEvent event = new CourseApprovedByAdminIntegrationEvent(uuid);

        // when
        sut.handleCourseApprovedByAdminEvent(event);

        // then
        final ArgumentCaptor<ApproveCourseCommand> argument = ArgumentCaptor.forClass(ApproveCourseCommand.class);
        verify(commandGateway).send(argument.capture());
        final ApproveCourseCommand approveCourseCommand = argument.getValue();
        assertThat(approveCourseCommand)
                .hasFieldOrPropertyWithValue("uuid", uuid);
    }

}
