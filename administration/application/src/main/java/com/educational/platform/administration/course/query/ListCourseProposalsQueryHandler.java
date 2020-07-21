package com.educational.platform.administration.course.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.educational.platform.administration.course.CourseProposalDTO;
import com.educational.platform.administration.course.CourseProposalRepository;

import lombok.RequiredArgsConstructor;

/**
 * Query handler for getting the course proposals by uuid.
 */
@RequiredArgsConstructor
@Component
public class ListCourseProposalsQueryHandler {

	private final CourseProposalRepository repository;

	/**
	 * Retrieves course proposals.
	 *
	 * @param query query.
	 * @return course proposals.
	 */
	@QueryHandler
	@PreAuthorize("hasRole('ADMIN')")
	@NonNull
	public List<CourseProposalDTO> handle(ListCourseProposalsQuery query) {
		return repository.listCourseProposals();
	}

}
