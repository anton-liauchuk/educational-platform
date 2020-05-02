package com.educational.platform.course.enrollments;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseEnrollmentFactoryTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    private CourseEnrollmentFactory sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = new CourseEnrollmentFactory(validator, courseRepository, studentRepository);
    }

    @Test
    void create_validCommand_courseEnrollmentSaved() {
        // given
        final UUID courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(courseId)
                .student("username")
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(courseId);
        final Course correspondingCourse = new Course(createCourseCommand);
        when(courseRepository.findByUuid(courseId)).thenReturn(Optional.of(correspondingCourse));

        final CreateStudentCommand createStudentCommand = new CreateStudentCommand("username");
        final Student correspondingStudent = new Student(createStudentCommand);
        when(studentRepository.findByUsername("username")).thenReturn(Optional.of(correspondingStudent));

        // when
        final CourseEnrollment enrollment = sut.createFrom(command);

        // then
        assertThat(enrollment).hasFieldOrPropertyWithValue("completionStatus", CompletionStatus.IN_PROGRESS);

        final Student student = (Student) ReflectionTestUtils.getField(enrollment, "student");
        assertThat(student).hasFieldOrPropertyWithValue("username", "username");

        final Course course = (Course) ReflectionTestUtils.getField(enrollment, "course");
        assertThat(course).hasFieldOrPropertyWithValue("uuid", courseId);
    }

    @Test
    void createFrom_courseIdIsNull_constraintViolationException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(null)
                .student("username")
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }

    @Test
    void createFrom_studentIdIsNull_constraintViolationException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .student(null)
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }

    @Test
    void createFrom_invalidCourseId_relatedResourceIsNotResolvedException() {
        // given
        final UUID courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(courseId)
                .student("username")
                .build();
        when(courseRepository.findByUuid(courseId)).thenReturn(Optional.empty());

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(RelatedResourceIsNotResolvedException.class, createAction);
    }

    @Test
    void createFrom_invalidStudentId_relatedResourceIsNotResolvedException() {
        // given
        final UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(uuid)
                .student("username")
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(uuid);
        final Course correspondingCourse = new Course(createCourseCommand);
        when(courseRepository.findByUuid(uuid)).thenReturn(Optional.of(correspondingCourse));
        when(studentRepository.findByUsername("username")).thenReturn(Optional.empty());

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(RelatedResourceIsNotResolvedException.class, createAction);
    }

}
