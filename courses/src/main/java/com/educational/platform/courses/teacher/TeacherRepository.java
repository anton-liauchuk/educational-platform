package com.educational.platform.courses.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents teacher repository.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
