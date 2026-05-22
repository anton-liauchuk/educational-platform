package com.educational.platform.course.enrollments.query;

import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.educational.platform.course.enrollments.CourseEnrollmentDTO;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;

/**
 * Query handler for getting the course enrollments.
 */
@Component
public class ListCourseEnrollmentsQueryHandler {

	private final CourseEnrollmentRepository repository;

    public ListCourseEnrollmentsQueryHandler(CourseEnrollmentRepository repository) {
        this.repository = repository;
    }

    /**
	 * Retrieves course enrollments.
	 *
	 * @param query query.
	 * @return corresponding course enrollment dtos.
	 */
	@Nonnull
	@PreAuthorize("hasRole('STUDENT')")
	public List<CourseEnrollmentDTO> handle(ListCourseEnrollmentsQuery query) {
		var principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var username = principal.getUsername();

		return repository.query(username);
	}

}
