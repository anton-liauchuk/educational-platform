package com.educational.platform.course.enrollments.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.educational.platform.course.enrollments.CourseEnrollment;
import com.educational.platform.course.enrollments.CourseEnrollmentRepository;
import com.educational.platform.course.enrollments.EnrollCourseRepository;
import com.educational.platform.course.enrollments.StudentRepository;

@Sql(scripts = "classpath:course.sql")
@DataJpaTest
public class CourseEnrollmentRepositoryTest {

	private static final String STUDENT = "student";
	private static final UUID FIRST_COURSE = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");
	private static final UUID SECOND_COURSE = UUID.fromString("123e4567-e89b-12d3-a456-426655440002");

	@Autowired
	private CourseEnrollmentRepository courseEnrollmentRepository;

	@Autowired
	private EnrollCourseRepository enrollCourseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void queryDtoByUUID_createdCourseEnrollment_dtoRetrieved() {
		// given
		createFirstCourseEnrollment();
		createSecondCourseEnrollment();

		// when
		var result = courseEnrollmentRepository.query(STUDENT);

		// then
		assertThat(result).isNotEmpty();
		assertThat(result).extracting("course").containsExactlyInAnyOrder(FIRST_COURSE, SECOND_COURSE);
	}

	private void createFirstCourseEnrollment() {
		var course = enrollCourseRepository.findByUuid(FIRST_COURSE);
		var student = studentRepository.findByUsername(STUDENT);
		var first = new CourseEnrollment(course.get(), student);
		courseEnrollmentRepository.save(first);
	}

	private void createSecondCourseEnrollment() {
		var course = enrollCourseRepository.findByUuid(SECOND_COURSE);
		var student = studentRepository.findByUsername(STUDENT);
		var second = new CourseEnrollment(course.get(), student);
		courseEnrollmentRepository.save(second);
	}

}
