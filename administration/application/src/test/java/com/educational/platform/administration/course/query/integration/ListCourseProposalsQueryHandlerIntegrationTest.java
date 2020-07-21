package com.educational.platform.administration.course.query.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.educational.platform.administration.course.CourseProposal;
import com.educational.platform.administration.course.CourseProposalRepository;
import com.educational.platform.administration.course.create.CreateCourseProposalCommand;
import com.educational.platform.administration.course.query.ListCourseProposalsQuery;
import com.educational.platform.administration.course.query.ListCourseProposalsQueryHandler;

@AutoConfigureTestDatabase
@SpringBootTest
public class ListCourseProposalsQueryHandlerIntegrationTest {

	@Autowired
	private CourseProposalRepository courseProposalRepository;

	@SpyBean
	private ListCourseProposalsQueryHandler sut;

	@BeforeEach
	void setUp() {
		var command = new CreateCourseProposalCommand(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"));
		courseProposalRepository.save(new CourseProposal(command));
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	void handle_validQuery_courseProposals() {
		// given
		var query = new ListCourseProposalsQuery();

		// when
		var result = sut.handle(query);

		// then
		assertThat(result).hasSize(1);
	}
}
