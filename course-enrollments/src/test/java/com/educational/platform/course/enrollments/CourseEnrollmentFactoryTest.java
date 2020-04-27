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
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(11)
                .studentId(22)
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(88);
        final Course correspondingCourse = new Course(createCourseCommand);
        when(courseRepository.findById(11)).thenReturn(Optional.of(correspondingCourse));

        final CreateStudentCommand createStudentCommand = new CreateStudentCommand(77);
        final Student correspondingStudent = new Student(createStudentCommand);
        when(studentRepository.findById(22)).thenReturn(Optional.of(correspondingStudent));

        // when
        final CourseEnrollment enrollment = sut.createFrom(command);

        // then
        final Student student = (Student) ReflectionTestUtils.getField(enrollment, "student");
        assertThat(student).hasFieldOrPropertyWithValue("originalStudentId", 77);

        final Course course = (Course) ReflectionTestUtils.getField(enrollment, "course");
        assertThat(course).hasFieldOrPropertyWithValue("originalCourseId", 88);
    }

    @Test
    void createFrom_courseIdIsNull_constraintViolationException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(null)
                .studentId(12)
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
                .courseId(11)
                .studentId(null)
                .build();

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(ConstraintViolationException.class, createAction);
    }

    @Test
    void createFrom_invalidCourseId_relatedResourceIsNotResolvedException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(11)
                .studentId(12)
                .build();
        when(courseRepository.findById(11)).thenReturn(Optional.empty());

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(RelatedResourceIsNotResolvedException.class, createAction);
    }

    @Test
    void createFrom_invalidStudentId_relatedResourceIsNotResolvedException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(11)
                .studentId(12)
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(88);
        final Course correspondingCourse = new Course(createCourseCommand);
        when(courseRepository.findById(11)).thenReturn(Optional.of(correspondingCourse));
        when(studentRepository.findById(12)).thenReturn(Optional.empty());

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(RelatedResourceIsNotResolvedException.class, createAction);
    }

}
