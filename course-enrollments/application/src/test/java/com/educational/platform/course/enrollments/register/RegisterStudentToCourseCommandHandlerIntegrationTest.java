package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.*;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.integration.event.StudentEnrolledToCourseIntegrationEvent;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@AutoConfigureTestDatabase
@SpringBootTest
public class RegisterStudentToCourseCommandHandlerIntegrationTest {

    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseEnrollmentFactory courseEnrollmentFactory;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    private RegisterStudentToCourseCommandHandler sut;

    private UUID courseUuid;
    private String studentUsername;

    @BeforeEach
    void setUp() {
        sut = new RegisterStudentToCourseCommandHandler(transactionTemplate, courseEnrollmentRepository, courseEnrollmentFactory, eventPublisher);

        courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final Course course = new Course(new CreateCourseCommand(courseUuid));
        courseRepository.save(course);

        studentUsername = "username";
        final Student student = new Student(new CreateStudentCommand(studentUsername));
        studentRepository.save(student);
    }

    @Test
    @WithMockUser(username = "username", roles = "STUDENT")
    void handle_validCommand_enrollmentSaved() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(courseUuid)
                .build();

        // when
        sut.handle(command);

        // then
        final ArgumentCaptor<StudentEnrolledToCourseIntegrationEvent> argument = ArgumentCaptor.forClass(StudentEnrolledToCourseIntegrationEvent.class);
        verify(eventPublisher).publishEvent(argument.capture());
        final StudentEnrolledToCourseIntegrationEvent event = argument.getValue();
        assertThat(event)
                .hasFieldOrPropertyWithValue("courseId", courseUuid)
                .hasFieldOrPropertyWithValue("username", "username");

        final CourseEnrollment enrollmentInEvent = (CourseEnrollment) ReflectionTestUtils.getField(event, "source");
        final UUID uuid = (UUID) ReflectionTestUtils.getField(enrollmentInEvent, "uuid");

        final Optional<CourseEnrollment> saved = courseEnrollmentRepository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
        final CourseEnrollment savedEnrollment = saved.get();
        final Student student = (Student) ReflectionTestUtils.getField(savedEnrollment, "student");
        assertThat(student).hasFieldOrPropertyWithValue("username", "username");

        final Course course = (Course) ReflectionTestUtils.getField(savedEnrollment, "course");
        assertThat(course).hasFieldOrPropertyWithValue("uuid", courseUuid);
    }
}
