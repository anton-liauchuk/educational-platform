package com.educational.platform.course.enrollments;


import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseEnrollmentTest {

    @Test
    void complete_completedStatus() {
        // given
        final RegisterStudentToCourseCommand registerStudentToCourseCommand = RegisterStudentToCourseCommand.builder()
                .courseId(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))
                .student("username")
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"));
        final CreateStudentCommand createStudentCommand = new CreateStudentCommand("username");
        final CourseEnrollment enrollment = new CourseEnrollment(registerStudentToCourseCommand, new Course(createCourseCommand), new Student(createStudentCommand));

        // when
        enrollment.complete();

        // then
        assertThat(enrollment)
                .hasFieldOrPropertyWithValue("completionStatus", CompletionStatus.COMPLETED);
    }

    @Test
    void toDTO_allFieldsAreFilled() {
        // given
        final UUID courseId = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
        final String username = "username";
        final RegisterStudentToCourseCommand registerStudentToCourseCommand = RegisterStudentToCourseCommand.builder()
                .courseId(courseId)
                .student(username)
                .build();
        final CreateCourseCommand createCourseCommand = new CreateCourseCommand(courseId);
        final CreateStudentCommand createStudentCommand = new CreateStudentCommand(username);
        final CourseEnrollment enrollment = new CourseEnrollment(registerStudentToCourseCommand, new Course(createCourseCommand), new Student(createStudentCommand));
        final UUID enrollmentId = (UUID) ReflectionTestUtils.getField(enrollment, "uuid");

        // when
        final CourseEnrollmentDTO dto = enrollment.toDTO();

        // then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("uuid", enrollmentId)
                .hasFieldOrPropertyWithValue("course", courseId)
                .hasFieldOrPropertyWithValue("student", username)
                .hasFieldOrPropertyWithValue("completionStatus", CompletionStatusDTO.IN_PROGRESS);
    }

}
