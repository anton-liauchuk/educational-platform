package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Represents student repository.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Retrieves a student by its username.
     *
     * @param username must not be {@literal null}.
     * @return the student with the given username or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    Optional<Student> findByUsername(String username);

}
