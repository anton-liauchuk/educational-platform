package com.educational.platform.course.enrollments.query;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import com.educational.platform.course.enrollments.CompletionStatusDTO;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommand;
import com.educational.platform.course.enrollments.register.RegisterStudentToCourseCommandHandler;

@Sql(scripts = "classpath:course.sql")
@AutoConfigureTestDatabase
@SpringBootTest
public class CourseEnrollmentByUUIDQueryHandlerIntegrationTest {

	private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

	@Autowired
	private RegisterStudentToCourseCommandHandler registerStudentToCourseCommandHandler;

	@SpyBean
	private CourseEnrollmentByUUIDQueryHandler sut;

    @Test
    @WithMockUser(username = "student", roles = "STUDENT")
	void handle_validCommand_courseSaved() {
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
