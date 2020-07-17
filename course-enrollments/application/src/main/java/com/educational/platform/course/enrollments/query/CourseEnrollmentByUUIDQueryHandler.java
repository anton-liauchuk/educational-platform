package com.educational.platform.course.enrollments.query;

import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.educational.platform.course.enrollments.CourseEnrollmentDTO;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;

import lombok.RequiredArgsConstructor;

/**
 * Query handler for getting the course enrollment by uuid.
 */
@RequiredArgsConstructor
@Component
public class CourseEnrollmentByUUIDQueryHandler {

	private final CourseEnrollmentRepository repository;

	/**
	 * Retrieves course enrollment by uuid.
	 *
	 * @param query query.
	 * @return corresponding course enrollment dto.
	 */
	@QueryHandler
	@NonNull
	@PreAuthorize("hasRole('STUDENT')")
	public Optional<CourseEnrollmentDTO> handle(CourseEnrollmentByUUIDQuery query) {
		var principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var username = principal.getUsername();

		return repository.query(query.getUuid(), username);
	}

}
