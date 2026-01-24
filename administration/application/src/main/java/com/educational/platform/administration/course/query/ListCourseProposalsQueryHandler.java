package com.educational.platform.administration.course.query;

import java.util.List;

import org.axonframework.messaging.queryhandling.annotation.QueryHandler;
import jakarta.annotation.Nonnull;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.inject.Named;

import com.educational.platform.administration.course.CourseProposalDTO;
import com.educational.platform.administration.course.CourseProposalRepository;

/**
 * Query handler for getting the course proposals by uuid.
 */
@Named
public class ListCourseProposalsQueryHandler {

	private final CourseProposalRepository repository;

    public ListCourseProposalsQueryHandler(CourseProposalRepository repository) {
        this.repository = repository;
    }

    /**
	 * Retrieves course proposals.
	 *
	 * @param query query.
	 * @return course proposals.
	 */
	@QueryHandler
	@PreAuthorize("hasRole('ADMIN')")
	@Nonnull
	public List<CourseProposalDTO> handle(ListCourseProposalsQuery query) {
		return repository.listCourseProposals();
	}

}
