package com.educational.platform.courses.course.query;

import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.educational.platform.courses.course.CourseDTO;
import com.educational.platform.courses.course.CourseRepository;

import lombok.RequiredArgsConstructor;

/**
 * Query handler for getting the course by uuid.
 */
@RequiredArgsConstructor
@Component
public class CourseByUUIDQueryHandler {

	private final CourseRepository repository;

	/**
	 * Retrieves course by uuid.
	 *
	 * @param query query.
	 * @return corresponding course dto.
	 */
	@QueryHandler
	@NonNull
	public Optional<CourseDTO> handle(CourseByUUIDQuery query) {
		return repository.findDTOByUuid(query.getUuid());
	}

}
