package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentDTO;
import com.educational.platform.course.enrollments.CourseEnrollmentFactory;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.integration.events.StudentEnrolledToCourseIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;

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

    /**
     * Creates course enrollment from command.
     *
     * @param command command
     */
    public void handle(RegisterStudentToCourseCommand command) {
        final CourseEnrollment courseEnrollment = transactionTemplate.execute(transactionStatus -> {
            final CourseEnrollment enrollment = courseEnrollmentFactory.createFrom(command);
            courseEnrollmentRepository.save(enrollment);

            return enrollment;
        });

        final CourseEnrollmentDTO courseEnrollmentDTO = Objects.requireNonNull(courseEnrollment).toDTO();

        eventPublisher.publishEvent(
                new StudentEnrolledToCourseIntegrationEvent(courseEnrollment,
                        courseEnrollmentDTO.getCourse(),
                        courseEnrollmentDTO.getStudent()));
    }

}
