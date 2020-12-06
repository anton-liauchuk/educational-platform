package com.educational.platform.course.reviews;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Represents the logic for checking if user is a reviewer of course review
 */
@RequiredArgsConstructor
@Component
public class CourseReviewChecker {

	private final CourseReviewRepository courseReviewRepository;

	public boolean hasAccess(Authentication authentication, UUID reviewId) {
		return courseReviewRepository.isReviewer(reviewId, authentication.getName());
	}

}
