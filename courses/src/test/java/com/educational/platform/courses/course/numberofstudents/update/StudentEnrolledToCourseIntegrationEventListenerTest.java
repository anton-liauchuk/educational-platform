package com.educational.platform.courses.course.numberofstudents.update;

import com.educational.platform.courses.course.numberofsudents.update.StudentEnrolledToCourseIntegrationEventListener;
import com.educational.platform.courses.course.numberofsudents.update.IncreaseNumberOfStudentsCommand;
import com.educational.platform.courses.course.numberofsudents.update.IncreaseNumberOfStudentsCommandHandler;
import com.educational.platform.integration.events.StudentEnrolledToCourseIntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentEnrolledToCourseIntegrationEventListenerTest {

    @Mock
    private IncreaseNumberOfStudentsCommandHandler handler;

    @InjectMocks
    private StudentEnrolledToCourseIntegrationEventListener sut;


    @Test
    void handleStudentEnrolledToCourseEvent_updateNumberOfStudentsCommandExecuted() {
        // given
        final StudentEnrolledToCourseIntegrationEvent event = new StudentEnrolledToCourseIntegrationEvent(new Object(), 15, "username");

        // when
        sut.handleStudentEnrolledToCourseEvent(event);

        // then
        final ArgumentCaptor<IncreaseNumberOfStudentsCommand> argument = ArgumentCaptor.forClass(IncreaseNumberOfStudentsCommand.class);
        verify(handler).handle(argument.capture());
        final IncreaseNumberOfStudentsCommand updateNumberOfStudentsCommand = argument.getValue();
        assertThat(updateNumberOfStudentsCommand)
                .hasFieldOrPropertyWithValue("id", 15);
    }

}
