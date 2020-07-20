package com.educational.platform.course.reviews.query;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.educational.platform.course.reviews.ReviewableCourse;
import com.educational.platform.course.reviews.ReviewableCourseRepository;
import com.educational.platform.course.reviews.Reviewer;
import com.educational.platform.course.reviews.ReviewerRepository;
import com.educational.platform.course.reviews.course.create.CreateReviewableCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommand;
import com.educational.platform.course.reviews.create.ReviewCourseCommandHandler;
import com.educational.platform.course.reviews.reviewer.create.CreateReviewerCommand;

@AutoConfigureTestDatabase
@SpringBootTest
public class ListCourseReviewsByCourseUUIDQueryHandlerIntegrationTest {

	private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

	@Autowired
	private ReviewableCourseRepository courseRepository;

	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private ReviewCourseCommandHandler reviewCourseCommandHandler;

	@SpyBean
	private ListCourseReviewsByCourseUUIDQueryHandler sut;

	@BeforeEach
	void setUp() {
		var course = new ReviewableCourse(new CreateReviewableCourseCommand(courseUuid));
		courseRepository.save(course);

		var reviewer = new Reviewer(new CreateReviewerCommand("reviewer"));
		reviewerRepository.save(reviewer);
	}

	@Test
	@WithMockUser(username = "reviewer", roles = "STUDENT")
	void handle_validCommand_courseSaved() {
		// given
		var command = ReviewCourseCommand.builder().courseId(courseUuid).comment("comment").rating(4.0).build();
		reviewCourseCommandHandler.handle(command);

		// when
		var result = sut.handle(new ListCourseReviewsByCourseUUIDQuery(courseUuid));

		// then
		assertThat(result).hasSize(1);
	}
}
