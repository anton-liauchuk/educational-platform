package com.educational.platform.courses.course.publish;

import com.educational.platform.courses.course.CourseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Represents the logic for checking if user is a teacher of course
 */
@Component
public class CourseTeacherChecker {

    private final CourseRepository courseRepository;

    public CourseTeacherChecker(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public boolean hasAccess(Authentication authentication, UUID courseId) {
        return courseRepository.isTeacher(courseId, authentication.getName());
    }

}
