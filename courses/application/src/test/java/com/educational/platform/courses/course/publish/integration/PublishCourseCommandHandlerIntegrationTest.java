package com.educational.platform.courses.course.publish.integration;

import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.PublishStatus;
import com.educational.platform.courses.course.create.CreateCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommand;
import com.educational.platform.courses.course.publish.PublishCourseCommandHandler;
import com.educational.platform.courses.teacher.Teacher;
import com.educational.platform.courses.teacher.TeacherRepository;
import com.educational.platform.courses.teacher.create.CreateTeacherCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PublishCourseCommandHandlerIntegrationTest {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @Autowired
    private TeacherRepository teacherRepository;

    @SpyBean
    private PublishCourseCommandHandler sut;

    // todo recheck duplicate teachers
    @BeforeEach
    void setUp() {
        var createTeacherCommand = new CreateTeacherCommand("username");
        var teacher = new Teacher(createTeacherCommand);
        teacherRepository.save(teacher);
    }

    @Test
    @WithMockUser(username = "username", roles = { "TEACHER" })
    void handle_existingCourse_courseSavedWithStatusPublished() {
        // given
        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course existingCourse = courseFactory.createFrom(createCourseCommand);
        existingCourse.approve();
        repository.save(existingCourse);

        final UUID uuid = (UUID) ReflectionTestUtils.getField(existingCourse, "uuid");
        final PublishCourseCommand command = new PublishCourseCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("publishStatus", PublishStatus.PUBLISHED);
    }
}
