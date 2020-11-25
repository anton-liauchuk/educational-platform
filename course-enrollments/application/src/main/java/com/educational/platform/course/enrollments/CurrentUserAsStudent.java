package com.educational.platform.course.enrollments;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.educational.platform.course.enrollments.student.Student;
import com.educational.platform.course.enrollments.student.StudentRepository;

/**
 * Represents the logic for retrieving the student entity from database for current authenticated user.
 */
@RequiredArgsConstructor
@Component
public class CurrentUserAsStudent {

    private final StudentRepository studentRepository;

    /**
     * Represents current user as student.
     *
     * @return teacher.
     */
    public Student userAsStudent() {
        var principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = principal.getUsername();

        return studentRepository.findByUsername(username);
    }

}
