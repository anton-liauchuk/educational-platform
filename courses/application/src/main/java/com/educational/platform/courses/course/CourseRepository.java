package com.educational.platform.courses.course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Represents course repository.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

	/**
	 * Retrieves a course by its uuid.
	 *
	 * @param uuid must not be {@literal null}.
	 * @return the course with the given uuid or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	Optional<Course> findByUuid(UUID uuid);

	/**
	 * Retrieves a course dto by its uuid.
	 *
	 * @param uuid must not be {@literal null}.
	 * @return the course dto with the given uuid or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	@Query(value = "SELECT new com.educational.platform.courses.course.CourseDTO(c.uuid, c.name, c.description, c.numberOfStudents) "
			+ "FROM com.educational.platform.courses.course.Course c WHERE c.uuid = :uuid")
	Optional<CourseDTO> findDTOByUuid(@Param("uuid") UUID uuid);

	/**
	 * Retrieves a list of course dtos.
	 *
	 * @return the list of course dtos.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	@Query(value = "SELECT new com.educational.platform.courses.course.CourseDTO(c.uuid, c.name, c.description, c.numberOfStudents) "
			+ "FROM com.educational.platform.courses.course.Course c")
	List<CourseDTO> list();

}
