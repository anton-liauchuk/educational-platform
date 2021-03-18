package com.educational.platform.courses.course;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

public interface CourseRepositoryCustom {

	/**
	 * Retrieves a course dto by its uuid.
	 *
	 * @param uuid must not be {@literal null}.
	 * @return the course dto with the given uuid or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	Optional<CourseDTO> findDTOByUuid(@Param("uuid") UUID uuid);

}
