package com.educational.platform.course.enrollments.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.educational.platform.course.enrollments.CompletionStatusDTO;
import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.course.enrollments.EnrollCourseRepository;
import com.educational.platform.course.enrollments.StudentRepository;

@Sql(scripts = "classpath:course.sql")
@DataJpaTest
public class CourseEnrollmentRepositoryTest {

	private static final String STUDENT = "student";

	@Autowired
	private CourseEnrollmentRepository courseEnrollmentRepository;

	@Autowired
	private EnrollCourseRepository enrollCourseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void queryDtoByUUID_createdCourseEnrollment_dtoRetrieved() {
		// given
		var course = enrollCourseRepository.findByUuid(UUID.fromString("123e4567-e89b-12d3-a456-426655440001"));
		var student = studentRepository.findByUsername(STUDENT);
		var courseEnrollment = new CourseEnrollment(course.get(), student);
		courseEnrollmentRepository.save(courseEnrollment);
		var uuid = courseEnrollment.toDTO().getUuid();

		// when
		var result = courseEnrollmentRepository.query(uuid, STUDENT);

		// then
		assertThat(result).isNotEmpty();
		var dto = result.get();
		assertThat(dto)
				.hasFieldOrPropertyWithValue("uuid", uuid)
				.hasFieldOrPropertyWithValue("course", course.get().toReference())
				.hasFieldOrPropertyWithValue("student", student.toReference())
				.hasFieldOrPropertyWithValue("completionStatus", CompletionStatusDTO.IN_PROGRESS);
	}

}
