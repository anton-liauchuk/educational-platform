package com.educational.platform.course.reviews.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import com.educational.platform.course.reviews.CourseReviewDTO;
import com.educational.platform.course.reviews.CourseReviewRepository;

/**
 * Query handler for getting the course reviews by course uuid.
 */
@Component
public class ListCourseReviewsByCourseUUIDQueryHandler {

	private final CourseReviewRepository repository;

    public ListCourseReviewsByCourseUUIDQueryHandler(CourseReviewRepository repository) {
        this.repository = repository;
    }

    /**
	 * Retrieves course reviews for particular course uuid.
	 *
	 * @param query query.
	 * @return course reviews.
	 */
	@QueryHandler
	@Nonnull
	public List<CourseReviewDTO> handle(ListCourseReviewsByCourseUUIDQuery query) {
		return repository.listCourseReviews(query.uuid());
	}

}
