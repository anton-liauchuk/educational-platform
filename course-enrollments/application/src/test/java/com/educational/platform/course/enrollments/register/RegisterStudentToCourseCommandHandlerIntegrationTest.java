package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.*;
import com.educational.platform.course.enrollments.course.EnrollCourse;
import com.educational.platform.course.enrollments.course.EnrollCourseRepository;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.integration.event.StudentEnrolledToCourseIntegrationEvent;
import com.educational.platform.course.enrollments.student.Student;
import com.educational.platform.course.enrollments.student.StudentRepository;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@AutoConfigureTestDatabase
@SpringBootTest
@EnableAutoConfiguration(exclude = { AxonAutoConfiguration.class, JpaAutoConfiguration.class, JpaEventStoreAutoConfiguration.class, JdbcAutoConfiguration.class })
public class RegisterStudentToCourseCommandHandlerIntegrationTest {

    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    private EnrollCourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseEnrollmentFactory courseEnrollmentFactory;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CurrentUserAsStudent currentUserAsStudent;

    @MockBean
    private EventBus eventBus;

    private RegisterStudentToCourseCommandHandler sut;

    private UUID courseUuid;
    private String studentUsername;

    @BeforeEach
    void setUp() {
        sut = new RegisterStudentToCourseCommandHandler(transactionTemplate, courseEnrollmentRepository, courseEnrollmentFactory, currentUserAsStudent, eventBus);

        courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final EnrollCourse course = new EnrollCourse(new CreateCourseCommand(courseUuid));
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
        var uuid = sut.handle(command);

        // then
        final ArgumentCaptor<GenericEventMessage<StudentEnrolledToCourseIntegrationEvent>> argument = ArgumentCaptor.forClass(GenericEventMessage.class);
        verify(eventBus).publish(argument.capture());
        final StudentEnrolledToCourseIntegrationEvent event = argument.getValue().getPayload();
        assertThat(event)
                .hasFieldOrPropertyWithValue("courseId", courseUuid)
                .hasFieldOrPropertyWithValue("username", "username");

        final Optional<CourseEnrollment> saved = courseEnrollmentRepository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
    }
}
