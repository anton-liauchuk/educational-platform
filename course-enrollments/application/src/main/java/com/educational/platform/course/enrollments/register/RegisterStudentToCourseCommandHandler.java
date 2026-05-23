package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentFactory;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.course.enrollments.CurrentUserAsStudent;
import com.educational.platform.course.enrollments.integration.event.StudentEnrolledToCourseIntegrationEvent;

import jakarta.annotation.Nonnull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.UUID;

/**
 * Command handler for {@link RegisterStudentToCourseCommand} registers student to course.
 */
@Component
@Transactional
public class RegisterStudentToCourseCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseEnrollmentFactory courseEnrollmentFactory;
    private final CurrentUserAsStudent currentUserAsStudent;
    private final ApplicationEventPublisher eventPublisher;

    public RegisterStudentToCourseCommandHandler(TransactionTemplate transactionTemplate, CourseEnrollmentRepository courseEnrollmentRepository, CourseEnrollmentFactory courseEnrollmentFactory, CurrentUserAsStudent currentUserAsStudent, ApplicationEventPublisher eventPublisher) {
        this.transactionTemplate = transactionTemplate;
        this.courseEnrollmentRepository = courseEnrollmentRepository;
        this.courseEnrollmentFactory = courseEnrollmentFactory;
        this.currentUserAsStudent = currentUserAsStudent;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Creates course enrollment from command.
     *
     * @param command command
     */
    @Nonnull
    @PreAuthorize("hasRole('STUDENT')")
    public UUID handle(RegisterStudentToCourseCommand command) {
        final CourseEnrollment courseEnrollment = transactionTemplate.execute(transactionStatus -> {
            final CourseEnrollment enrollment = courseEnrollmentFactory.createFrom(command);
            courseEnrollmentRepository.save(enrollment);

            return enrollment;
        });

        final UUID uuid = Objects.requireNonNull(courseEnrollment).getUuid();
        eventPublisher.publishEvent(new StudentEnrolledToCourseIntegrationEvent(command.courseId(),
                currentUserAsStudent.userAsStudent().toReference()));

        return uuid;
    }

}
