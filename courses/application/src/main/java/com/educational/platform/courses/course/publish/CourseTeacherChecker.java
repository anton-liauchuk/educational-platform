package com.educational.platform.courses.course.publish;

import com.educational.platform.courses.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Represents the logic for checking if user is a teacher of course
 */
@RequiredArgsConstructor
@Component
public class CourseTeacherChecker {

    private final CourseRepository courseRepository;

    public boolean hasAccess(Authentication authentication, UUID courseId) {
        var course = courseRepository.findByUuid(courseId);
        return course.map(value -> value.isTeacher(authentication.getName())).orElse(false);
    }

}
