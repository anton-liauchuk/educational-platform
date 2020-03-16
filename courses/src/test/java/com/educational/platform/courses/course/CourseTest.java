package com.educational.platform.courses.course;


import com.educational.platform.courses.course.create.CreateCourseCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    void publish_publishedStatus() {
        // given
        final Course course = new Course();

        // when
        course.publish();

        // then
        assertThat(course).hasFieldOrPropertyWithValue("status", Status.PUBLISHED);
    }


    @Test
    void create_validCommand_createdCourse() {
        // given
        final CreateCourseCommand command = new CreateCourseCommand("name", "description");

        // when
        final Course course = new Course(command);

        // then
        assertThat(course).hasFieldOrPropertyWithValue("name", "name")
                .hasFieldOrPropertyWithValue("description", "description");
    }

}
