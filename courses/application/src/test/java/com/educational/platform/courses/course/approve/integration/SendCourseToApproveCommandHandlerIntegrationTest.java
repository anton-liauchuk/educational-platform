package com.educational.platform.courses.course.approve.integration;

import com.educational.platform.courses.course.ApprovalStatus;
import com.educational.platform.courses.course.Course;
import com.educational.platform.courses.course.CourseFactory;
import com.educational.platform.courses.course.CourseRepository;
import com.educational.platform.courses.course.approve.SendCourseToApproveCommand;
import com.educational.platform.courses.course.approve.SendCourseToApproveCommandHandler;
import com.educational.platform.courses.course.create.CreateCourseCommand;
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
public class SendCourseToApproveCommandHandlerIntegrationTest {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseFactory courseFactory;

    @Autowired
    private TeacherRepository teacherRepository;

    @SpyBean
    private SendCourseToApproveCommandHandler sut;

    // todo recheck duplicate teachers
    @BeforeEach
    void setUp() {
        var createTeacherCommand = new CreateTeacherCommand("username");
        var teacher = new Teacher(createTeacherCommand);
        teacherRepository.save(teacher);
    }

    @Test
    @WithMockUser(username = "username", roles = {"TEACHER"})
    void handle_existingCourse_courseSavedWithStatusWaitingForApproval() {
        // given
        final CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
                .name("name")
                .description("description")
                .build();
        final Course existingCourse = courseFactory.createFrom(createCourseCommand);
        repository.save(existingCourse);

        final UUID uuid = (UUID) ReflectionTestUtils.getField(existingCourse, "uuid");
        final SendCourseToApproveCommand command = new SendCourseToApproveCommand(uuid);

        // when
        sut.handle(command);

        // then
        final Optional<Course> saved = repository.findByUuid(uuid);
        assertThat(saved).isNotEmpty();
        final Course course = saved.get();
        assertThat(course).hasFieldOrPropertyWithValue("approvalStatus", ApprovalStatus.WAITING_FOR_APPROVAL);
    }
}
