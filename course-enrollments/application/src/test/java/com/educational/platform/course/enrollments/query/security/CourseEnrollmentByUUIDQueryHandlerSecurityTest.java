package com.educational.platform.course.enrollments.query.security;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import com.educational.platform.course.enrollments.query.CourseEnrollmentByUUIDQuery;
import com.educational.platform.course.enrollments.query.CourseEnrollmentByUUIDQueryHandler;

@Sql(scripts = "classpath:course.sql")
@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class CourseEnrollmentByUUIDQueryHandlerSecurityTest {

	@SpyBean
	private CourseEnrollmentByUUIDQueryHandler sut;

	@Test
	@WithMockUser(roles = "TEACHER")
	void handle_userIsTeacher_accessDeniedException() {
		// given
		var command = new CourseEnrollmentByUUIDQuery(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"));

		// when
		final ThrowingCallable queryAction = () -> sut.handle(command);

		// then
		assertThatThrownBy(queryAction).hasRootCauseInstanceOf(AccessDeniedException.class);
	}
}
