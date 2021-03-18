package com.educational.platform.courses.course;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<CourseDTO> findDTOByUuid(UUID uuid) {
		CourseDTO result = (CourseDTO) entityManager
				.createQuery(
						"select course.uuid as course_uuid, course.name as course_name, course.description as course_description, course.numberOfStudents as course_numberOfStudents, "
								+ " curriculumItems.serialNumber as curriculumItems_serialNumber, curriculumItems.uuid as curriculumItems_uuid, curriculumItems.title as curriculumItems_title, curriculumItems.description as curriculumItems_description, curriculumItems.class as curriculumItems_type"
								+ " from com.educational.platform.courses.course.Course course left join course.curriculumItems curriculumItems WHERE course.uuid = :uuid")
				.setParameter("uuid", uuid)
				.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(new CourseDTOResultTransformer())
				.getSingleResult();

		return Optional.ofNullable(result);
	}
}
