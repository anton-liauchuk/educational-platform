package com.educational.platform.course.enrollments.query;

import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import jakarta.annotation.Nonnull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.educational.platform.course.enrollments.CourseEnrollmentDTO;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;

/**
 * Query handler for getting the course enrollment by uuid.
 */
@Component
public class CourseEnrollmentByUUIDQueryHandler {

	private final CourseEnrollmentRepository repository;

    public CourseEnrollmentByUUIDQueryHandler(CourseEnrollmentRepository repository) {
        this.repository = repository;
    }

    /**
	 * Retrieves course enrollment by uuid.
	 *
	 * @param query query.
	 * @return corresponding course enrollment dto.
	 */
	@QueryHandler
	@Nonnull
	@PreAuthorize("hasRole('STUDENT')")
	public Optional<CourseEnrollmentDTO> handle(CourseEnrollmentByUUIDQuery query) {
		var principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var username = principal.getUsername();

		return repository.query(query.uuid(), username);
	}

}
