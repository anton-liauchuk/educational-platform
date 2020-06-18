package com.educational.platform.course.reviews;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Represents the logic for checking if user is a reviewer of course review
 */
@RequiredArgsConstructor
@Component
public class CourseReviewChecker {

    private final CourseReviewRepository courseReviewRepository;

    public boolean hasAccess(Authentication authentication, UUID reviewId) {
        var courseReview = courseReviewRepository.findByUuid(reviewId);
        return courseReview.map(value -> value.isReviewer(authentication.getName())).orElse(false);
    }

}
