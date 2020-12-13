package com.educational.platform.courses.course.query;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import com.educational.platform.courses.teacher.create.CreateTeacherCommand;

@AutoConfigureTestDatabase
@SpringBootTest
public class ListCourseQueryHandlerIntegrationTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ListCourseQueryHandler sut;

	@BeforeEach
	void setUp() {
		var teacher = new Teacher(new CreateTeacherCommand("teacher"));
		teacherRepository.save(teacher);

		var course = new Course(CreateCourseCommand.builder().name("name").description("description").build(), teacher.getId());
		courseRepository.save(course);
	}

	@Test
	void handle_validQuery_courseRetrieved() {
		// given
		var query = new ListCourseQuery();

		// when
		var result = sut.handle(query);

		// then
		assertThat(result).isNotEmpty();
		var dto = result.get(0);
		assertThat(dto).hasFieldOrPropertyWithValue("name", "name").hasFieldOrPropertyWithValue("description", "description");
	}
}
