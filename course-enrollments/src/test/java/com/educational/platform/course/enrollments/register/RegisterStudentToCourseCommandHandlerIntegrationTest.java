package com.educational.platform.course.enrollments.register;

import com.educational.platform.course.enrollments.*;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;
import com.educational.platform.integration.events.StudentEnrolledToCourseIntegrationEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

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

    private Integer courseId;
    private Integer studentId;

    @BeforeEach
    void setUp() {
        sut = new RegisterStudentToCourseCommandHandler(transactionTemplate, courseEnrollmentRepository, courseEnrollmentFactory, eventPublisher);

        final Course course = new Course(new CreateCourseCommand(44));
        courseRepository.save(course);
        courseId = (Integer) ReflectionTestUtils.getField(course, "id");

        final Student student = new Student(new CreateStudentCommand(55, "username"));
        studentRepository.save(student);
        studentId = (Integer) ReflectionTestUtils.getField(course, "id");
    }

    @Test
    void handle_validCommand_enrollmentSaved() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(courseId)
                .studentId(studentId)
                .build();

        // when
        sut.handle(command);

        // then
        final Optional<CourseEnrollment> saved = courseEnrollmentRepository.findOne(Example.of(courseEnrollmentFactory.createFrom(command)));
        assertThat(saved).isNotEmpty();
        final CourseEnrollment enrollment = saved.get();
        final Student student = (Student) ReflectionTestUtils.getField(enrollment, "student");
        assertThat(student).hasFieldOrPropertyWithValue("username", "username");

        final Course course = (Course) ReflectionTestUtils.getField(enrollment, "course");
        assertThat(course).hasFieldOrPropertyWithValue("originalCourseId", 44);

        final ArgumentCaptor<StudentEnrolledToCourseIntegrationEvent> argument = ArgumentCaptor.forClass(StudentEnrolledToCourseIntegrationEvent.class);
        verify(eventPublisher).publishEvent(argument.capture());
        final StudentEnrolledToCourseIntegrationEvent event = argument.getValue();
        assertThat(event)
                .hasFieldOrPropertyWithValue("courseId", 44)
                .hasFieldOrPropertyWithValue("username", "username");
    }
}
