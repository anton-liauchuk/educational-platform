package com.educational.platform.course.enrollments.query;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.educational.platform.course.enrollments.CompletionStatusDTO;
import com.educational.platform.course.enrollments.EnrollCourse;
import com.educational.platform.course.enrollments.EnrollCourseRepository;
import com.educational.platform.course.enrollments.Student;
import com.educational.platform.course.enrollments.StudentRepository;
import com.educational.platform.course.enrollments.course.create.CreateCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;
import com.educational.platform.course.enrollments.student.create.CreateStudentCommand;

@AutoConfigureTestDatabase
@SpringBootTest
public class CourseEnrollmentByUUIDQueryHandlerIntegrationTest {

	private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

	@Autowired
	private EnrollCourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RegisterStudentToCourseCommandHandler registerStudentToCourseCommandHandler;

	@SpyBean
	private CourseEnrollmentByUUIDQueryHandler sut;

	@BeforeEach
	void setUp() {
		var course = new EnrollCourse(new CreateCourseCommand(courseUuid));
		courseRepository.save(course);

		var student = new Student(new CreateStudentCommand("student"));
		studentRepository.save(student);
	}

	@Test
	@WithMockUser(username = "student", roles = "STUDENT")
	void handle_validQuery_courseEnrollmentRetrieved() {
		// given
		var command = RegisterStudentToCourseCommand.builder().courseId(courseUuid).build();
		var identifier = registerStudentToCourseCommandHandler.handle(command);

		// when
		var result = sut.handle(new CourseEnrollmentByUUIDQuery(identifier));

		// then
		assertThat(result).isNotEmpty();
		var dto = result.get();
		assertThat(dto)
				.hasFieldOrPropertyWithValue("uuid", identifier)
				.hasFieldOrPropertyWithValue("course", courseUuid)
				.hasFieldOrPropertyWithValue("student", "student")
				.hasFieldOrPropertyWithValue("completionStatus", CompletionStatusDTO.IN_PROGRESS);
	}
}
