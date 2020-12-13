package com.educational.platform.course.reviews.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.educational.platform.course.reviews.CourseReviewRepository;

@Sql(scripts = "classpath:course_review.sql")
@DataJpaTest
public class CourseReviewRepositoryTest {

	public static final UUID COURSE_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000");
	public static final UUID COURSE_REVIEW_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
	public static final String REVIEWER_USERNAME = "reviewer";

	@Autowired
	private CourseReviewRepository sut;

	@Test
	void listCourseReviews_unpaged_courseReviews() {
		// given/when
		var result = sut.listCourseReviews(COURSE_UUID);

		// then
		assertThat(result).hasSize(1);
	}

	@Test
	void isReviewer_validReviewer_true() {
		// given/when
		var result = sut.isReviewer(COURSE_REVIEW_UUID, REVIEWER_USERNAME);

		// then
		assertThat(result).isTrue();
	}

	@Test
	void isReviewer_inValidReviewer_false() {
		// given/when
		var result = sut.isReviewer(COURSE_REVIEW_UUID, "another-reviewer");

		// then
		assertThat(result).isFalse();
	}
}
