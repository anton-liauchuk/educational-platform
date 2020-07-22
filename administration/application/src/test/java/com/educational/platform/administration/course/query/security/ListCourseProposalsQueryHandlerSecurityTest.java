package com.educational.platform.administration.course.query.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.query.ListCourseProposalsQuery;
import com.educational.platform.administration.course.query.ListCourseProposalsQueryHandler;

@SpringBootTest(properties = "com.educational.platform.security.enabled=true")
public class ListCourseProposalsQueryHandlerSecurityTest {

	@MockBean
	private CourseProposalRepository courseProposalRepository;

	@SpyBean
	private ListCourseProposalsQueryHandler sut;

	@Test
	@WithMockUser(roles = "TEACHER")
	void handle_userIsTeacher_accessDeniedException() {
		// given
		var query = new ListCourseProposalsQuery();

		// when
		final ThrowingCallable queryAction = () -> sut.handle(query);

		// then
		assertThatThrownBy(queryAction).hasRootCauseInstanceOf(AccessDeniedException.class);
	}

	@Test
	@WithMockUser(roles = "STUDENT")
	void handle_userIsStudent_accessDeniedException() {
		// given
		var query = new ListCourseProposalsQuery();

		// when
		final ThrowingCallable queryAction = () -> sut.handle(query);

		// then
		assertThatThrownBy(queryAction).hasRootCauseInstanceOf(AccessDeniedException.class);
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void handle_userIsAdmin_result() {
		// given
		var query = new ListCourseProposalsQuery();
		doReturn(new ArrayList<>()).when(courseProposalRepository).listCourseProposals();

		// when
		var result = sut.handle(query);

		// then
		assertThat(result).isEmpty();
	}
}
