package com.educational.platform.administration.course.create;

import com.educational.platform.integration.events.SendCourseToApproveIntegrationEvent;
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
public class SendCourseToApproveIntegrationEventListenerTest {

    @Mock
    private CreateCourseProposalCommandHandler handler;

    @InjectMocks
    private SendCourseToApproveIntegrationEventListener sut;

    @Test
    void handleCourseApprovedByAdminEvent_approveCourseCommandExecuted() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final SendCourseToApproveIntegrationEvent event = new SendCourseToApproveIntegrationEvent(new Object(), uuid);

        // when
        sut.handleSendCourseToApproveEvent(event);

        // then
        final ArgumentCaptor<CreateCourseProposalCommand> argument = ArgumentCaptor.forClass(CreateCourseProposalCommand.class);
        verify(handler).handle(argument.capture());
        final CreateCourseProposalCommand createCourseProposalCommand = argument.getValue();
        assertThat(createCourseProposalCommand)
                .hasFieldOrPropertyWithValue("uuid", uuid);
    }

}
