package com.educational.platform.courses.course.approve;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.ApprovalStatus;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.integration.events.SendCourseToApproveIntegrationEvent;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendCourseToApproveCommandHandlerTest {

    private CourseFactory courseFactory;

    @Mock
    private CourseRepository repository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private SendCourseToApproveCommandHandler sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        courseFactory = new CourseFactory(validator);
    }

    @Test
    void handle_existingCourse_courseSavedWithStatusWaitingForApproval() {
        // given
        final SendCourseToApproveCommand command = new SendCourseToApproveCommand(15);

        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course correspondingCourse = courseFactory.createFrom(createCourseCommand);
        when(repository.findById(15)).thenReturn(Optional.of(correspondingCourse));

        // when
        sut.handle(command);

        // then
        final ArgumentCaptor<SendCourseToApproveIntegrationEvent> argument = ArgumentCaptor.forClass(SendCourseToApproveIntegrationEvent.class);
        verify(eventPublisher).publishEvent(argument.capture());
        assertThat(argument.getValue())
                .hasFieldOrPropertyWithValue("courseId", 15);
        final Course course = (Course) argument.getValue().getSource();
        assertThat(course)
                .hasFieldOrPropertyWithValue("name", "name")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("approvalStatus", ApprovalStatus.WAITING_FOR_APPROVAL);
    }


    @Test
    void handle_invalidId_resourceNotFoundException() {
        // given
        final SendCourseToApproveCommand command = new SendCourseToApproveCommand(15);
        when(repository.findById(15)).thenReturn(Optional.empty());

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(command);

        // then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(handle);
    }
}
