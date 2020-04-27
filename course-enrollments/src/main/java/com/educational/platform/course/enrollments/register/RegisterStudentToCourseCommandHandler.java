package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentFactory;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Command handler for {@link RegisterStudentToCourseCommand} registers student to course.
 */
@RequiredArgsConstructor
@Component
@Transactional
public class RegisterStudentToCourseCommandHandler {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseEnrollmentFactory courseEnrollmentFactory;

    /**
     * Creates course enrollment from command.
     *
     * @param command command
     */
    public void handle(RegisterStudentToCourseCommand command) {
        final CourseEnrollment enrollment = courseEnrollmentFactory.createFrom(command);
        courseEnrollmentRepository.save(enrollment);
    }

}
