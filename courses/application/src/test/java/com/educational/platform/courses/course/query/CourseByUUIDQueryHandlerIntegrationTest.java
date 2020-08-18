package com.educational.platform.courses.course.query;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.create.CreateCourseCommandHandler;
import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import com.educational.platform.courses.teacher.create.CreateTeacherCommand;

@AutoConfigureTestDatabase
@SpringBootTest
public class CourseByUUIDQueryHandlerIntegrationTest {

	private final UUID courseUuid = UUID.fromString("123e4567-e89b-12d3-a456-426655440001");

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private CreateCourseCommandHandler createCourseCommandHandler;

	@SpyBean
	private CourseByUUIDQueryHandler sut;

	@BeforeEach
	void setUp() {
		var teacher = new Teacher(new CreateTeacherCommand("teacher"));
		teacherRepository.save(teacher);
	}

	@Test
	@WithMockUser(username = "teacher", roles = "TEACHER")
	void handle_validQuery_courseRetrieved() {
		// given
		final CreateCourseCommand command = CreateCourseCommand.builder().name("name").description("description").build();
		var identifier = createCourseCommandHandler.handle(command);

		// when
		var result = sut.handle(new CourseByUUIDQuery(identifier));

		// then
		assertThat(result).isNotEmpty();
		var dto = result.get();
		assertThat(dto)
				.hasFieldOrPropertyWithValue("uuid", identifier)
				.hasFieldOrPropertyWithValue("name", "name")
				.hasFieldOrPropertyWithValue("description", "description");
	}
}
