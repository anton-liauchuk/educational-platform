package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents student repository.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
