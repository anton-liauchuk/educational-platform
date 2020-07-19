package com.educational.platform.course.reviews;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents course review repository.
 */
public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer> {

    /**
     * Retrieves a course review by its uuid.
     *
     * @param uuid must not be {@literal null}.
     * @return the course review with the given uuid or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal uuid} is {@literal null}.
     */
    Optional<CourseReview> findByUuid(UUID uuid);

    /**
     * Retrieves list of course reviews.
     *
     * @param pageable pageable.
     * @param uuid course uuid.
     * @return page of course review.
     */
    @Query("select new com.educational.platform.course.reviews.CourseReviewDTO(cr.uuid, c.originalCourseId, r.username, cr.comment.comment, cr.rating.rating) from CourseReview cr \n"
            + "join com.educational.platform.course.reviews.ReviewableCourse c on cr.course = c.id\n"
            + "join com.educational.platform.course.reviews.Reviewer r on cr.reviewer =  r.id")
    Page<CourseReviewDTO> listCourseReviews(Pageable pageable, UUID uuid);

}
