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
    private EnrollCourseRepository courseRepository;

    @Mock
    private CurrentUserAsStudent currentUserAsStudent;

    private CourseEnrollmentFactory sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = new CourseEnrollmentFactory(validator, courseRepository, currentUserAsStudent);
    }

    @Test
    void create_validCommand_courseEnrollmentSaved() {
        // given
        final UUID courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(courseId)
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(courseId);
        final EnrollCourse correspondingCourse = new EnrollCourse(createCourseCommand);
        when(courseRepository.findByUuid(courseId)).thenReturn(Optional.of(correspondingCourse));

        final CreateStudentCommand createStudentCommand = new CreateStudentCommand("username");
        final Student correspondingStudent = new Student(createStudentCommand);
        when(currentUserAsStudent.userAsStudent()).thenReturn(correspondingStudent);

        // when
        final CourseEnrollment enrollment = sut.createFrom(command);

        // then
        assertThat(enrollment).hasFieldOrPropertyWithValue("completionStatus", CompletionStatus.IN_PROGRESS);

        final Student student = (Student) ReflectionTestUtils.getField(enrollment, "student");
        assertThat(student).hasFieldOrPropertyWithValue("username", "username");

        final EnrollCourse course = (EnrollCourse) ReflectionTestUtils.getField(enrollment, "course");
        assertThat(course).hasFieldOrPropertyWithValue("uuid", courseId);
    }

    @Test
    void createFrom_courseIdIsNull_constraintViolationException() {
        // given
        final RegisterStudentToCourseCommand command = RegisterStudentToCourseCommand.builder()
                .courseId(null)
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
                .build();
        when(courseRepository.findByUuid(courseId)).thenReturn(Optional.empty());

        // when
        final Executable createAction = () -> sut.createFrom(command);

        // then
        assertThrows(RelatedResourceIsNotResolvedException.class, createAction);
    }

}
