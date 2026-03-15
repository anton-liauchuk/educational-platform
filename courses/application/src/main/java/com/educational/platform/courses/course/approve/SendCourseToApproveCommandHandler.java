package com.educational.platform.courses.course.approve;

import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseAlreadyApprovedException;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.integration.event.SendCourseToApproveIntegrationEvent;

import org.axonframework.messaging.commandhandling.annotation.CommandHandler;
import org.axonframework.messaging.core.MessageType;
import org.axonframework.messaging.eventhandling.EventBus;
import org.axonframework.messaging.eventhandling.GenericEventMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Command handler for {@link SendCourseToApproveCommand}, send a course to approve.
 */
@Component
@Transactional
public class SendCourseToApproveCommandHandler {

    private final CourseRepository repository;
    private final EventBus eventBus;

    public SendCourseToApproveCommandHandler(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    /**
     * Handles send course to approve command.
     *
     * @param command command
     * @throws CourseAlreadyApprovedException if course was already approved
     * @throws ResourceNotFoundException      if resource not found
     */
    @CommandHandler
    @PreAuthorize("hasRole('TEACHER') and @courseTeacherChecker.hasAccess(authentication, #c.uuid)")
    public void handle(@P("c") SendCourseToApproveCommand command) {
        final Optional<Course> dbResult = repository.findByUuid(command.uuid());
        if (dbResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Course with uuid: %s not found", command.uuid()));
        }

        final Course course = dbResult.get();
        course.sendToApprove();

        // todo integration event outside transaction
        eventBus.publish(null, new GenericEventMessage(new MessageType(SendCourseToApproveIntegrationEvent.class), new SendCourseToApproveIntegrationEvent(command.uuid())));
    }
}
