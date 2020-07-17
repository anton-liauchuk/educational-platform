package com.educational.platform.course.enrollments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course enrollment repository.
 */
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {

    /**
     * Retrieves a course enrollment by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course enrollment with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<CourseEnrollment> findByUuid(UUID uuid);

    /**
     * Retrieves a course enrollment dto by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course enrollment dto with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    @Query(value = "SELECT new com.educational.platform.course.enrollments.CourseEnrollmentDTO(ce.uuid, ec.uuid, s.username, ce.completionStatus) "
            + "FROM com.educational.platform.course.enrollments.CourseEnrollment ce JOIN com.educational.platform.course.enrollments.EnrollCourse ec "
            + "ON ce.course = ec.id JOIN com.educational.platform.course.enrollments.Student s ON ce.student = s.id "
            + "WHERE ce.uuid = :uuid and ce.student.username = :student")
    Optional<CourseEnrollmentDTO> query(@Param("uuid") UUID uuid, @Param("student") String student);

}
