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
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseRepositoryCustom {

	/**
	 * Retrieves a course by its uuid.
	 *
	 * @param uuid must not be {@literal null}.
	 * @return the course with the given uuid or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	Optional<Course> findByUuid(UUID uuid);

	/**
	 * Retrieves a list of course dtos.
	 *
	 * @return the list of course dtos.
	 * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
	 */
	@Query(value = "SELECT new com.educational.platform.courses.course.CourseLightDTO(c.uuid, c.name, c.description, c.numberOfStudents) "
			+ "FROM com.educational.platform.courses.course.Course c")
	List<CourseLightDTO> list();

	/**
	 * Checks if passed username is an username of teacher of course.
	 *
	 * @param uuid course uuid.
	 * @param username username.
	 * @return true if teacher, false if not.
	 */
	@Query("select count(c) > 0 from Course c join com.educational.platform.courses.teacher.Teacher r on c.teacher = r.id where c.uuid = :uuid and r.username = :username")
	boolean isTeacher(@Param("uuid") UUID uuid, @Param("username") String username);

}
