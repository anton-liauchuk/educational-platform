package com.educational.platform.courses.course;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents course repository.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
