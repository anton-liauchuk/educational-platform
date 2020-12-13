package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentFactory;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.course.enrollments.CurrentUserAsStudent;
import com.educational.platform.course.enrollments.integration.event.StudentEnrolledToCourseIntegrationEvent;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.UUID;

/**
 * Command handler for {@link RegisterStudentToCourseCommand} registers student to course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class RegisterStudentToCourseCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseEnrollmentFactory courseEnrollmentFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final CurrentUserAsStudent currentUserAsStudent;

    /**
     * Creates course enrollment from command.
     *
     * @param command command
     */
    @CommandHandler
    @NonNull
    @PreAuthorize("hasRole('STUDENT')")
    public UUID handle(RegisterStudentToCourseCommand command) {
        final CourseEnrollment courseEnrollment = transactionTemplate.execute(transactionStatus -> {
            final CourseEnrollment enrollment = courseEnrollmentFactory.createFrom(command);
            courseEnrollmentRepository.save(enrollment);

            return enrollment;
        });

        final UUID uuid = Objects.requireNonNull(courseEnrollment).getUuid();

        eventPublisher.publishEvent(
                new StudentEnrolledToCourseIntegrationEvent(courseEnrollment,
                        command.getCourseId(),
                        currentUserAsStudent.userAsStudent().toReference()));

        return uuid;
    }

}
