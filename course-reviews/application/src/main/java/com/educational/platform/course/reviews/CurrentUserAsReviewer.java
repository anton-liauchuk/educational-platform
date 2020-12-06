package com.educational.platform.course.reviews;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.educational.platform.course.reviews.reviewer.Reviewer;
import com.educational.platform.course.reviews.reviewer.ReviewerRepository;

/**
 * Represents the logic for retrieving the reviewer entity from database for current authenticated user.
 */
@RequiredArgsConstructor
@Component
public class CurrentUserAsReviewer {

    private final ReviewerRepository reviewerRepository;

    /**
     * Represents current user as reviewer.
     *
     * @return teacher.
     */
    public Reviewer userAsReviewer() {
        var principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = principal.getUsername();

        return reviewerRepository.findByUsername(username);
    }

}
